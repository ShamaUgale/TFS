package com.sgti.tfsdemo;

import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.WorkItemClient;
import com.microsoft.tfs.core.clients.workitem.fields.Field;
import com.microsoft.tfs.core.clients.workitem.link.LinkFactory;
import com.microsoft.tfs.core.clients.workitem.link.RelatedLink;
import com.microsoft.tfs.core.clients.workitem.link.WorkItemLinkType;
import com.microsoft.tfs.core.clients.workitem.link.WorkItemLinkTypeEnd;
import com.microsoft.tfs.core.clients.workitem.project.Project;

/**
 * Created by Navin.Theruvingal on 21-11-2015.
 */
public class AssignTaskToStory {
    public static void main(String[] args) {
        TFSTeamProjectCollection tpc = SnippetSettings.connectToTFS();

        Project project = tpc.getWorkItemClient().getProjects().get(SnippetSettings.PROJECT_NAME);
        WorkItemClient workItemClient = project.getWorkItemClient();

        WorkItem storyItem = workItemClient.getWorkItemByID(7);
        WorkItem taskItem = workItemClient.getWorkItemByID(8);

        /*for (WorkItemLinkTypeEnd wiLinkType: storyItem.getClient().getLinkTypes().getLinkTypeEnds()) {
            System.out.println(wiLinkType.getID() + "\t" + wiLinkType.getName());
        }*/


        // Create a related link between the work items.
        String linkComment = "Assign Task to Story";
        RelatedLink newRelatedLink =
                LinkFactory.newRelatedLink(storyItem, taskItem, 2, linkComment, false);

        // Add the link to the first new work item.
        storyItem.getLinks().add(newRelatedLink);
        storyItem.save();
        System.out.println("Added a link to work item.");


    }
}
