package support;

import io.restassured.response.Response;
import org.json.JSONObject;

import static support.World.*;

public class SitesApiHelper {

    private String email = emailId;
    private String password = PropertiesReader.applicationPassword;
    private Response response;

    public SitesApiHelper withPassword(String password) {
        this.password = password;
        return this;
    }

    public String temperatureUnitValue() {
        if (accountTemperatureUnit.equalsIgnoreCase("Fahrenheit")) {
            tempUnit = "F";
        } else {
            tempUnit = "C";
        }
        return tempUnit;
    }

    public String pressureUnitValue() {
        if (accountPressureUnit.equalsIgnoreCase("Millibars")) {
            presUnit = "mb";
        } else {
            presUnit = "kp";
        }
        System.out.println("sites api helper page  Pressure Unit :- " + presUnit);
        return presUnit;
    }

    public String sizeUnitValue() {
        if (accountSizeUnit.equalsIgnoreCase("Inches")) {
            sizeUnit = "in";
        } else {
            sizeUnit = "mm";
        }
        return sizeUnit;
    }

    public String speedUnitValue() {
        if (accountSpeedUnit.equalsIgnoreCase("Miles per hour")) {
            speedUnit = "mph";
        } else if (accountSpeedUnit.equalsIgnoreCase("Kilometer per hour")) {
            speedUnit = "kph";
        } else {
            speedUnit = "mps";
        }
        return speedUnit;
    }

    public JSONObject buildAuthRequest() {
        return new JSONObject()
                .put("email", email)
                .put("password", password);
    }

    public String getAuthToken() {
        restAssuredHelper.setBaseURI(PropertiesReader.apiSeverUrl);
        restAssuredHelper.setContentType("application/json");
        String authPayload = new SitesApiHelper()
                .withPassword(password)
                .buildAuthRequest()
                .toString();
        restAssuredHelper.setRequestBody(authPayload);
        response = restAssuredHelper.postRequest(PropertiesReader.apiVersion + "/auth/token");
        token = response.path("token");
        return token;
    }

    public void getSummaryRequest() {
        tempUnit = sitesApiHelper.temperatureUnitValue();
        presUnit = sitesApiHelper.pressureUnitValue();
        sizeUnit = sitesApiHelper.sizeUnitValue();
        speedUnit = sitesApiHelper.speedUnitValue();
        String summaryUrl = "?temp=" + tempUnit + "&pres=" + presUnit + "&size=" + sizeUnit + "&speed=" + speedUnit +
                "&select=time,updated,et,etc,et_10d,lfairdelta,ndvi,cl,rh,slp,slp_low,slp_high,tair,sun,leaftemp,fcst_max,fcst_min,fcst_precip,fcst_wind_speed,fcst_wind_direction,precip,precip_10d,cgdd,wind_speed,wind_heading,wind_direction,soil_moisture&is_operational=true";
        restAssuredHelper.setBaseURI(PropertiesReader.apiSeverUrl);
        restAssuredHelper.setAuthToken(token);
        response = restAssuredHelper.getRequest(PropertiesReader.apiVersion + "/sites/" + siteID + "/summary" + summaryUrl);
        tAir = stringHelper.floatToString(response.path("summary.measurements.tair"));
        precipitation = stringHelper.floatToString(response.path("summary.measurements.precip"));
        sun = stringHelper.floatToString(response.path("summary.measurements.sun"));
        slp = stringHelper.floatToString(response.path("summary.measurements.slp"));
        rh = stringHelper.decimalToPercentage(response.path("summary.measurements.rh"));
    }
}