Feature: Map | Site weather tab

  @Performance
  Scenario Outline: Map | Capture page load time for map page with multiple "<role>" user
    Given assign testcase "<tcId>" to log data to TestRail
    Given log into application with a "<role>" user
    When navigate to "map" page
    Then verify site name is displayed and update Excel with tme taken sheet for "<role>" user

    Examples:
      | role      | tcId  |
      | orgAdmin  | 63708 |

    @sanity
  Scenario Outline: Map | Capture page load time for map page with multiple "<role>" user
    Given assign testcase "<tcId>" to log data to TestRail
    Given log into application with a "<role>" user
    When navigate to "map" page
  #  Then verify temp rain and solar is sorted
    Then verify measurement with units in legend

    Examples:
      | role      | tcId  |
      | orgAdmin  | 63708 |