Feature: Login

  @sanity
  Scenario Outline: Login | verify login with valid credentials with multiple "<role>" user
    Given assign testcase "<tcId>" to log data to TestRail
    Given log into application with valid credential for different "<role>" user


    Examples:
      | role      | tcId  |
      | orgAdmin  | 58972 |

