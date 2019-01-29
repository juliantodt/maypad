package de.fraunhofer.iosb.maypadbackend.model.deployment;

import de.fraunhofer.iosb.maypadbackend.model.webhook.ExternalWebhook;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Deployment where a webhook should be called.
 *
 * @author Lukas Brosch
 * @version 1.0
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WebhookDeployment extends DeploymentType {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ExternalWebhook deploymentWebhook;

    /**
     * Constructor for WebhookDeployment.
     * @param deploymentWebhook the webhook used for deploying
     */
    public WebhookDeployment(ExternalWebhook deploymentWebhook) {
        this.deploymentWebhook = deploymentWebhook;
    }

    /**
     * Constructor for WebhookDeployment.
     * @param deploymentWebhook the webhook used for deploying
     * @param name Name of the deployment
     */
    public WebhookDeployment(ExternalWebhook deploymentWebhook, String name) {
        super(name);
        this.deploymentWebhook = deploymentWebhook;
    }

    @Override
    public String toString() {
        return deploymentWebhook.getUrl();
    }
}
