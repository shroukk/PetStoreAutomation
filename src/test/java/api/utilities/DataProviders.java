package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {
    @DataProvider(name = "UserData")
    public Object [][] getAllData() throws IOException {
        return XLUtility.getCellsData("src/test/resources/testdata/petStoreData.xlsx", "UserData");
    }
    @DataProvider(name = "UserNames")
    public Object[] getUserNames() throws IOException {
        return XLUtility.getUsernamesCells("src/test/resources/testdata/petStoreData.xlsx", "UserData");
//        Object[][] data = new Object[usernames.length][1];
//        for (int i = 0; i < usernames.length; i++) {
//            data[i][0] = usernames[i];
//        }
//        return data;
   }

}
