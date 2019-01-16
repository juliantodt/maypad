package de.fraunhofer.iosb.maypadbackend.config.project.data;

import lombok.Data;

import java.util.List;

@Data
public class ProjectConfigData {
    private String projectName;
    private String projectDescription;
    private boolean allBranches;
    private List<BranchProperty> branches;

    public boolean getAllBranches() {
        return allBranches;
    }
}
