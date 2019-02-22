package de.fraunhofer.iosb.maypadbackend.services.scheduler;

import de.fraunhofer.iosb.maypadbackend.config.server.ServerConfig;
import de.fraunhofer.iosb.maypadbackend.model.Project;
import de.fraunhofer.iosb.maypadbackend.repositories.ProjectRepository;
import de.fraunhofer.iosb.maypadbackend.services.reporefresh.RepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Schedule the project updates.
 */
@Service
public class SchedulerService {
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private Map<Integer, ScheduledFuture<?>> taskMapping;
    private ServerConfig serverConfig;
    private RepoService repoService;
    private ProjectRepository projectRepository;

    private static final Logger logger = LoggerFactory.getLogger(SchedulerService.class);

    /**
     * Initializes the threadPoolTaskScheduler and the taskMapping.
     *
     * @param serverConfig      the serverconfig
     * @param projectRepository the ProjectRepository used to access project
     * @param repoService       the RepoService used to update repositories
     */
    @Autowired
    public SchedulerService(ServerConfig serverConfig, RepoService repoService, ProjectRepository projectRepository,
                            ThreadPoolTaskScheduler scheduler) {
        this.serverConfig = serverConfig;
        this.repoService = repoService;
        this.projectRepository = projectRepository;
        this.threadPoolTaskScheduler = scheduler;
        taskMapping = new ConcurrentHashMap<>();
    }

    /**
     * Adds a project to the scheduling.
     *
     * @param projectId the id of the project that should be updated
     */
    public void scheduleRepoRefresh(int projectId) {
        if (!taskMapping.containsKey(projectId)) {
            logger.info("Scheduled Project " + projectId);
            RefreshTask task = new RefreshTask(projectId, repoService);
            ScheduledFuture<?> sf = threadPoolTaskScheduler.scheduleWithFixedDelay(task,
                    Instant.now().plusSeconds(serverConfig.getReloadRepositoriesSeconds()),
                    Duration.ofSeconds(serverConfig.getReloadRepositoriesSeconds()));
            taskMapping.put(projectId, sf);
        }
    }


    /**
     * Removes a project from the scheduling.
     *
     * @param projectId the id of the project that should be unscheduled
     */
    public void unscheduleRepoRefresh(int projectId) {
        if (taskMapping.containsKey(projectId)) {
            logger.info("Unscheduled Project " + projectId);
            ScheduledFuture<?> sf = taskMapping.get(projectId);
            sf.cancel(false);
            taskMapping.remove(projectId);
        }
    }

    @PostConstruct
    protected void init() {
        for (Project project : projectRepository.findAll()) {
            logger.info("Scheduled Project " + project.getId());
            RefreshTask task = new RefreshTask(project.getId(), repoService);
            ScheduledFuture<?> sf = threadPoolTaskScheduler.scheduleWithFixedDelay(task,
                    serverConfig.getReloadRepositoriesSeconds() * 1000L);
            taskMapping.put(project.getId(), sf);
        }
    }
}
