package sample.test.java;

import org.junit.Test;
import sample.model.User;
import sample.model.WaterSource;
import sample.model.WaterSourceReport;
import sample.model.WaterSourceReportList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Allen on 11/14/2016.
 */
public class addReportTester {
    /**
     * testing when input is null
     */
    @Test
    public void testNullInupt(){
        WaterSourceReportList tem = new WaterSourceReportList();
        boolean thrown = false;
        try {
            tem.addReport(null);
        } catch(IllegalArgumentException e){
            thrown = true;
        }
        assertTrue(thrown);
    }
    /**
     * testing when input is not null
     */
    @Test
    public void testNotNullInput(){

        WaterSourceReport tem = new WaterSourceReport();
        WaterSourceReportList temL = new WaterSourceReportList();
        temL.addReport(tem);
        assertEquals("report is not stored",temL.getBackingArray().get(0),tem);
    }

}
