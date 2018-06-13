package com.sgti;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.sgti.tfsdemo.DemoTfs;
import com.sgti.tfsdemo.SnippetSettings;

public class Main {

    public static void main(String[] args) {
        TFSTeamProjectCollection tpc = SnippetSettings.connectToTFS();
        Project project = tpc.getWorkItemClient().getProjects().get(SnippetSettings.PROJECT_NAME);
        DemoTfs demoTfs = new DemoTfs();
        int userStoryId = demoTfs.createUserStory(project);
        int taskId = demoTfs.createTask(project);
        demoTfs.addAttachmentToWI(project, userStoryId, "C:\\abc\\Agile change management.png");
        demoTfs.addAttachmentToWI(project, taskId, "C:\\abc\\Agile change management.png");
        demoTfs.addCommentToWI(project, userStoryId, "This will take some time");
        demoTfs.addCommentToWI(project, userStoryId, "This is coming along");
        demoTfs.addCommentToWI(project, taskId, "Need more info on this task");
        demoTfs.attachTaskToStory(project, userStoryId, taskId);
    }
}
