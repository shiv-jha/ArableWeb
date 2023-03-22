package support;


import org.openqa.selenium.WebDriver;

public class Constants {
    public final static String warningMsg = "This site will NOT be assigned to any team. If you wish to assign it to a specific team please go to the Teams Page first and use the Edit Teams to add the device to the team.";
    public final static String clearBoundaryBtnText = "Clear Boundary";
    public final static String errorText = "Error Saving";

    public final static String languagePt = "Português";
    public final static String languageEng = "English";
    public final static String languageTranslatedText = "Idioma";

    public final static int siteCardCount = 9;

    public final static String copySeasonText = "Copy Season to New Site(s)";
    public final static String summaryTitleMsg = "Created seasons";
    public final static String deleteSeasonSuccessMsg = "Growing season successfully deleted";
    public final static String copySeasonTitle = "Copy Season to New Site(s)";
    public final static String copySeasonTitleDesc = "Please select a site, or sites, to assign them a crop type and varietal.";
    public final static String unassignedSitesSuccess = "Site have been asigned";
    public final static String unassignedSitesModalTitle = "Unassigned Sites";
    public final static String unassignedSitesModalSubTitle = "The followings sites have not been assigned to any teams";

    public final static String assignedTeamModalHeader = "Assigned Teams";
    public final static String assignedTeamModalSubHeader = "Teams in the Organization that has at least one Site associated to it.";

    public final static String unassignedTeamModalHeader = "Unassigned Teams";
    public final static String unassignedTeamModalSubHeader = "Teams that has no Site associated to it.";

    public final static String assignedMemberModalHeader = "Assigned Members";
    public final static String assignedMemberModalSubHeader = "The following are Members in the Organization that has at least one Team associated to it.";
    public final static String unassignedMemberModalHeader = "Unassigned Members";
    public final static String unassignedMemberModalSubHeader = "The following are Members with no teams associated.";
    public final static String unassignedMemberNoDataModalDesc = "The count of Members that has no Team associated to it.";
    public final static String assignedSitesModalHeader = "Assigned Sites";
    public final static String assignedSitesModalDesc = "The count of Sites in the Organization that has at least one Team associated to it.";
    public final static String ModalDescNoData = "There are no information to show";
    public final static String teamsColumnModalDesc = "A Team label groups a set of users with a set of devices for the purpose of managing the fleet efficiently. Only the Org Admins and the Members of the team has permission to see the devices and its deployments.";
    public final static String firstColumnName = "Team Name";
    public final static String secondColumnName = "Members";

    public final static String thirdColumnName = "Sites";
    public final static String sitesColumnModalDesc = "A site is a physical area with a shared set of specific characteristics, e.g., crop type, treatment, soil and/or irrigation practices, etc. In practice, a site corresponds to a field or an irrigation block and is created by drawing its boundaries in the Sites module. There may be one or more Arable Mark devices associated with a site. If there are multiple Mark devices assigned to a site, the system will aggregate the measurements to the site level.";
    public final static String membersColumnModalDesc = "The members of a Team can be divided into Team Admins (has permission to invite other users to the Team) and Team Members (can view other members and the devices of the Team). If you don't see your name and email address as part of a Team that you expected to be in, then contact one of the Org Admins that are listed in the top row as they have visibility of all Devices and Teams in your organization and can invite you to the correct Team using the Invite Member button.";
    public final static String createOrgBtn = "Create New Organization";
    public final static String confirmBtnText = "Confirm";
    public final static String assignedDevicesModalHeader = "Assigned Devices";
    public final static String assignedDevicesModalDesc = "To view the devices associated to a specific organization, please select the organization from the “Source” drop-down menu.";
    public final static String assignedDevicesModalSteps = "If you wish to move any devices from one organization to another, please select the devices you wish to move from the source organization, then select an organization from “Destination” the drop-down, and press Return.";
    public final static String assignedDevicesModalOrgSource = "Source";
    public final static String assignedDevicesModalOrgDest = "Destination";
    public final static String assignedDevicesModalActionBtn = "Return";
    public final static String unassignedDevicesModalHeader = "Unassigned Devices";
    public final static String unassignedDevicesModalSteps = "The devices on this list have not yet been assigned to any Organization. To assign them please select the organization you wish to assign a device from the “Destination” drop-down, select the device(s) you wish to assign from the list, and press Assign.";
    public final static String unassignedDevicesModalOrgSource = "Source";
    public final static String unassignedDevicesModalOrgDest = "Destination";
    public final static String unassignedDevicesModalActionBtn = "Assign";
    public final static String distributorFirstColumnHeader = "Organization Name";
    public final static String distributorSecondColumnHeader = "Members";
    public final static String distributorThirdColumnHeader = "Devices";
    public final static String OrgDetailsHeader = "Devices Assigned to this Organization";

    public final static String ciModalHeader = "Chlorophyll Index";
    public final static String ciModalText = "Chlorophyll Index\n" +
            "The chlorophyll index (green band formulation) is a proxy for plant" +
            " productivity as well as the chlorophyll and nitrogen content in the plant" +
            " during peak greenness. The Mark's spectrometer detects reflected light in" +
            " the green and near-infrared regions of the spectrum to calculate a" +
            " cholrophyll index. A higher chlorophyll index is indicative of higher" +
            " chlorophyll and nitrogen levels in the canopy and higher productivity in the" +
            " plant.";
    private final WebDriver driver;

    public Constants(WebDriver driver) {
        this.driver = driver;
    }

}
