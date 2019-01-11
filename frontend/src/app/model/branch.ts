import { BuildStatus } from './buildStatus';
import { Commit } from './commit';

export class Branch {
    name: string;
    projectName: string;
    deployment: string;
    members: string[];
    mails: string[];
    dependencies: string[];
    readme: string;
    buildWebhook: string;
    buildSuccessURL: string;
    buildFailURL: string;
    tags: string[];
    status: BuildStatus;
    lastCommit: Commit;
}
