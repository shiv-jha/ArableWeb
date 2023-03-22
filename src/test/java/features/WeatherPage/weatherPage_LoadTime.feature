Feature: Weather | Site weather tab

  @Performance	
  Scenario Outline: Weather | Verify current conditions table and its values for "<role>" user
    Given assign testcase "<tcId>" to log data to TestRail
    Given log into application with a "<role>" user
    When navigate to "Search" page and search the site and click on the site card
    Then user is on site weather tab and verify the current conditions table and update excel for "<role>" user.

    Examples:
      | role      | tcId  |
      | orgAdmin  | 60835 |
      
  @sanity
  Scenario Outline: Weather |  verify the device count in Site's Device List with weather Page
    Given assign testcase "<tcId>" to log data to TestRail
    Given log into application with a "<role>" user
    When navigate to "Search" page and click on Specific Site and go to site detail page
    Then verify mark lbl with device list count

    Examples:
      | role      | tcId  |
      | orgAdmin  | 60836 |
