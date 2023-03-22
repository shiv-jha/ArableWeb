Feature: Seasons Page

  @Performance
    @sanity
  Scenario Outline: Season | Capture page load for season page with multiple "<role>" user
    Given assign testcase "<tcId>" to log data to TestRail
    Given user logg into application with a "<role>" user
    When navigate to "Seasons" and verify season table is displayed for "<role>" user
    Then write in Excel for "<role>" user the time taken to load the page

    Examples:
      | role      | tcId  |
      | orgAdmin  | 63702 |
