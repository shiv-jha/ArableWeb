Feature: Account Page

  @sanity @Performance
  Scenario Outline: Account | verify generating,copying & refreshing API key with multiple "<role>" user
    Given assign testcase "<tcId>" to log data to TestRail
    Given log into application with a "<role>" user
    When navigate to Settings and then to "Account" page
    Then verify lbls are translating into portugal
   # Then verify generating,copying & refreshing for "<role>" user


    Examples:
      | role      | tcId  |
      | orgAdmin  | 63208 |