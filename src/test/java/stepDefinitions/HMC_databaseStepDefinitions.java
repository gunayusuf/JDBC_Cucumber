package stepDefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;

public class HMC_databaseStepDefinitions {

    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; " +
            "user=techproed;password=P2s@rt65";
    String username="techproed";
    String password="P2s@rt65";
    Connection connection; // Database'e baglanmamizi saglar
    Statement statement; // Query calistirmamizi sagliyor
    ResultSet resultSet; // Query sonucunda donen sonucu store etmemize yariyor

    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        connection= DriverManager.getConnection(url,username,password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        String query="select "+ field + " from "+ table ;
        resultSet=statement.executeQuery(query);
    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {

        // resultset iterator gibi calisir

        resultSet.first();
        System.out.println(resultSet.getString("Price")); // 225.000
        resultSet.next(); // iterator'a benzer sekilde calisir
                          // next()'u imleci bir sonraki degerin yanina goturur
                          // bize true veya false doner
        System.out.println(resultSet.getString("Price")); // 400.000

        System.out.println(resultSet.next()); // true yada false yazdirir
        System.out.println(resultSet.getString("Price")); //445.000
        // next() kullanilirken cok dikkatli olmaliyiz
        // cunku nerede olursa olsun imleci bir sonraki elemente gecirecektir

        System.out.println("==================LİSTE====================");

        resultSet.absolute(0);
        int sira=1;
        while (resultSet.next()){
            System.out.println(sira+". kayit : " + resultSet.getString("Price"));
            sira++;
            // 425.000 den yazdirmaya basladi cunku basta next() methodu
            // nerede kaldiysa oradan calismaya devam eder.
            // resultset.absolute(0) dan baslattik
            // bunu onlemek icin int sira=1
            //System.out.println(sira+". kayit : " + resultSet.getString("Price"));
            // sira++; seklinde yazdik
        }


        //tabloda kac satir oldugunu nasil buluruz ?

        resultSet.last();
        System.out.println("satir sayisi : " + resultSet.getRow()); // 416




    }

}
