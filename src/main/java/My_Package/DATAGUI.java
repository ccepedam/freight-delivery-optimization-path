/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;


import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.net.ProtocolException;


import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.nio.file.Paths;
import java.util.Properties;


/**
 *
 * @author CARLOSANDRES
 */
public class DATAGUI extends javax.swing.JFrame implements Global{

    /**
     * Creates new form DATAGUI
     */
    int NCities = 0;
    int NClients = 0;
    double FC = 1000000000;
    double K1 = 0;
    double K2 = 0;
    double PGas = 0;
    double PSalary = 0;
    int Betha = 1000000000;
    int Alpha = Betha*1000; 

    //String rootPath = System.getProperty("user.dir");
    String rootPath = "/pathToRootOfProject";


    String PathShortestDistanceFile = rootPath + "/data/ShortDist.csv";
    String PathShortestDistanceClientsFile = rootPath + "/data/ShortDistClients.csv";

    String privateKey = "ReplaceWithPrivateKeyGoogleMapsAPI";
    
    int size=0;
    int [][] distances;
    int [][] distancesIni;
    double [][] times;
    int [][] speeds;
    int [] [] ClientData;
    String [] Cities; //This is the ListCities.csv
    int [] [] ShortDistances;
    int [] [] ShortDistancesClients;
    int [] ClientsInOrder;
    int [] DistancesInOrder;
    double Revenue=0;
    double Profit=0;
    double TCost=0;
    int minCost = 0;
    
    String Tour=null;
    
    int countOrders = 0;
    String OrigenCity="";
    
    ArrayList<String[]> ordersData = new ArrayList<String[]>();
    
    String [][] ordersDataGroup;
    Set<String> orignesU = new HashSet<>();
    Set<String> destinationsU = new HashSet<>();
    List<String> allCitiesList = new ArrayList<String>();
    List<OrderT> myOrders = new ArrayList<OrderT>();
    
    String ordersSumary = "";
    String URLToVisit = "";
    
    
    public class OrderT {
        private String OrigenCity;
        private String DestinationCity;
        private int VolumeTotal;
        private int WeightTotal;
        private List<Integer> idOrders = new ArrayList<Integer>(); 
        private List<Integer> VolumeOrders = new ArrayList<Integer>();
        private List<Integer> WeightOrders = new ArrayList<Integer>(); 
        
        public OrderT() {
            OrigenCity = "";
            DestinationCity = "";
            VolumeTotal = 0;
            WeightTotal = 0;
        }
        
        public void SetOrigen (String OriCity){
            OrigenCity = OriCity;
        }
        
        public void SetDestination (String DesCity){
            DestinationCity = DesCity;
        }
        
        public String GetOrigen (){
            return OrigenCity;
        }
        
        public String GetDestination (){
            return DestinationCity;
        }
        
        public void insertData (int idOR, int dataWeight, int dataVolume){
            idOrders.add(idOR);
            WeightOrders.add(dataWeight);
            VolumeOrders.add(dataVolume);
        }
        
        public int GetVolumeTotal(){
            VolumeTotal = 0;
            VolumeOrders.forEach((val) -> {
                VolumeTotal = VolumeTotal+val;
            });
            return VolumeTotal;
        }
        
        public int GetWeightTotal(){
            WeightTotal = 0;
            WeightOrders.forEach((val) -> {
                WeightTotal = WeightTotal+val;
            });
            return WeightTotal;
        }
        
        public String getStringOrder(){
            String OrderDesc = "";
            OrderDesc = OrderDesc + "Origin City: " + OrigenCity + "\n";
            OrderDesc = OrderDesc + "Destination City: " + DestinationCity + "\n";
            OrderDesc = OrderDesc + "Total Number of Ordes: " + VolumeOrders.size() + "\n";
            OrderDesc = OrderDesc + "Weight Total: " + Integer.toString(this.GetWeightTotal()) + "\n";
            OrderDesc = OrderDesc + "Volumen Total: " + Integer.toString(this.GetVolumeTotal()) + "\n";
            OrderDesc = OrderDesc + "Orders (Weight, Volumen): " + "\n";
            for (int i = 0; i < WeightOrders.size(); i++){
                OrderDesc = OrderDesc + "ID: " + Integer.toString(idOrders.get(i)) + " " + "(" + Integer.toString(WeightOrders.get(i)) + ", " + Integer.toString(VolumeOrders.get(i)) + ")" + "\n";
            }
            return OrderDesc;
        }
    }
    
        
    public DATAGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_FC = new javax.swing.JTextField();
        jTextField_K1 = new javax.swing.JTextField();
        jTextField_K2 = new javax.swing.JTextField();
        jTextField_PGas = new javax.swing.JTextField();
        jTextField_PSalary = new javax.swing.JTextField();
        jButton_Run = new javax.swing.JButton();
        jButton_Exit = new javax.swing.JButton();
        jButton_Load = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Out = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_Profit = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        OriginList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        DetinationList = new javax.swing.JList<>();
        VolumeIn = new javax.swing.JTextField();
        WeightIn = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        addOrder = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        totalOrders = new javax.swing.JTextField();
        totalCities = new javax.swing.JLabel();
        totalCitiesDis = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        OriginCity = new javax.swing.JList<>();
        addOrigen = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        mapShow = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("MAIN MENU FDS");

        jLabel3.setText("Fixed Cost of Operation");

        jLabel4.setText("K1 ($/(lb*in^2*mi))");

        jLabel5.setText("K2 (depreciation)");

        jLabel6.setText("$gas/mi");

        jLabel7.setText("Salary/hour");

        jTextField_FC.setText("500");

        jTextField_K1.setText("0.000003");

        jTextField_K2.setText("0.1");

        jTextField_PGas.setText("0.17");

        jTextField_PSalary.setText("20");

        jButton_Run.setText("RUN");
        jButton_Run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RunActionPerformed(evt);
            }
        });

        jButton_Exit.setText("EXIT");
        jButton_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExitActionPerformed(evt);
            }
        });

        jButton_Load.setText("Load Data");
        jButton_Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadActionPerformed(evt);
            }
        });

        jTextArea_Out.setColumns(20);
        jTextArea_Out.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Out);

        jLabel10.setText("Tour -Results");

        jLabel14.setText("Profit");

        jTextField_Profit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ProfitActionPerformed(evt);
            }
        });

        OriginList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Abbeville,GA", "Acworth,GA", "Adairsville,GA", "Adel,GA", "Adrian,GA", "Ailey,GA", "Alamo,GA", "Alapaha,GA", "Albany,GA", "Aldora,GA", "Allenhurst,GA", "Allentown,GA", "Alma,GA", "Alpharetta,GA", "Alston,GA", "Alto,GA", "Ambrose,GA", "Americus,GA", "Andersonville,GA", "Arabi,GA", "Aragon,GA", "Arcade,GA", "Argyle,GA", "Arlington,GA", "Arnoldsville,GA", "Ashburn,GA", "Athens[b],GA", "Atlanta,GA", "Attapulgus,GA", "Auburn,GA", "Augusta[c],GA", "Austell,GA", "Avalon,GA", "Avera,GA", "Avondale Estates,GA", "Baconton,GA", "Bainbridge,GA", "Baldwin,GA", "Ball Ground,GA", "Barnesville,GA", "Bartow,GA", "Barwick,GA", "Baxley,GA", "Bellville,GA", "Berkeley Lake,GA", "Berlin,GA", "Bethlehem,GA", "Between,GA", "Bishop,GA", "Blackshear,GA", "Blairsville,GA", "Blakely,GA", "Bloomingdale,GA", "Blue Ridge,GA", "Bluffton,GA", "Blythe,GA", "Bogart,GA", "Boston,GA", "Bostwick,GA", "Bowdon,GA", "Bowersville,GA", "Bowman,GA", "Braselton,GA", "Braswell,GA", "Bremen,GA", "Brinson,GA", "Bronwood,GA", "Brookhaven[d],GA", "Brooklet,GA", "Brooks,GA", "Broxton,GA", "Brunswick,GA", "Buchanan,GA", "Buckhead,GA", "Buena Vista,GA", "Buford,GA", "Butler,GA", "Byromville,GA", "Byron,GA", "Cadwell,GA", "Cairo,GA", "Calhoun,GA", "Camak,GA", "Camilla,GA", "Canon,GA", "Canton,GA", "Carl,GA", "Carlton,GA", "Carnesville,GA", "Carrollton,GA", "Cartersville,GA", "Cave Spring,GA", "Cecil,GA", "Cedartown,GA", "Centerville,GA", "Centralhatchee,GA", "Chamblee,GA", "Chatsworth,GA", "Chattahoochee Hills[e],GA", "Chauncey,GA", "Chester,GA", "Chickamauga,GA", "Clarkesville,GA", "Clarkston,GA", "Claxton,GA", "Clayton,GA", "Clermont,GA", "Cleveland,GA", "Climax,GA", "Cobbtown,GA", "Cochran,GA", "Cohutta,GA", "Colbert,GA", "College Park,GA", "Collins,GA", "Colquitt,GA", "Columbus[f],GA", "Comer,GA", "Commerce,GA", "Concord,GA", "Conyers,GA", "Coolidge,GA", "Cordele,GA", "Cornelia,GA", "Covington,GA", "Crawford,GA", "Crawfordville,GA", "Culloden,GA", "Cumming,GA", "Cusseta[g],GA", "Cuthbert,GA", "Dacula,GA", "Dahlonega,GA", "Daisy,GA", "Dallas,GA", "Dalton,GA", "Damascus,GA", "Danielsville,GA", "Danville,GA", "Darien,GA", "Dasher,GA", "Davisboro,GA", "Dawson,GA", "Dawsonville,GA", "Dearing,GA", "Decatur,GA", "Deepstep,GA", "Demorest,GA", "Denton,GA", "De Soto,GA", "Dexter,GA", "Dillard,GA", "Doerun,GA", "Donalsonville,GA", "Dooling,GA", "Doraville,GA", "Douglas,GA", "Douglasville,GA", "Dublin,GA", "Dudley,GA", "Duluth,GA", "Dunwoody,GA", "Du Pont,GA", "East Dublin,GA", "East Ellijay,GA", "Eastman,GA", "East Point,GA", "Eatonton,GA", "Echols County[h],GA", "Edge Hill,GA", "Edison,GA", "Elberton,GA", "Ellaville,GA", "Ellenton,GA", "Ellijay,GA", "Emerson,GA", "Enigma,GA", "Ephesus,GA", "Eton,GA", "Euharlee,GA", "Fairburn,GA", "Fairmount,GA", "Fargo,GA", "Fayetteville,GA", "Fitzgerald,GA", "Flemington,GA", "Flovilla,GA", "Flowery Branch,GA", "Folkston,GA", "Forest Park,GA", "Forsyth,GA", "Fort Gaines,GA", "Fort Oglethorpe,GA", "Fort Valley,GA", "Franklin,GA", "Franklin Springs,GA", "Funston,GA", "Gainesville,GA", "Garden City,GA", "Garfield,GA", "Gay,GA", "Geneva,GA", "Georgetown,GA", "Gibson,GA", "Gillsville,GA", "Girard,GA", "Glennville,GA", "Glenwood,GA", "Good Hope,GA", "Gordon,GA", "Graham,GA", "Grantville,GA", "Gray,GA", "Grayson,GA", "Greensboro,GA", "Greenville,GA", "Griffin,GA", "Grovetown,GA", "Gumbranch,GA", "Guyton,GA", "Hagan,GA", "Hahira,GA", "Hamilton,GA", "Hampton,GA", "Hapeville,GA", "Haralson,GA", "Harlem,GA", "Harrison,GA", "Hartwell,GA", "Hawkinsville,GA", "Hazlehurst,GA", "Helen,GA", "Helena,GA", "Hephzibah,GA", "Hiawassee,GA", "Higgston,GA", "Hiltonia,GA", "Hinesville,GA", "Hiram,GA", "Hoboken,GA", "Hogansville,GA", "Holly Springs,GA", "Homeland,GA", "Homer,GA", "Homerville,GA", "Hoschton,GA", "Hull,GA", "Ideal,GA", "Ila,GA", "Iron City,GA", "Irwinton,GA", "Ivey,GA", "Jackson,GA", "Jacksonville,GA", "Jakin,GA", "Jasper,GA", "Jefferson,GA", "Jeffersonville,GA", "Jenkinsburg,GA", "Jersey,GA", "Jesup,GA", "Johns Creek [i],GA", "Jonesboro,GA", "Junction City,GA", "Kennesaw,GA", "Keysville,GA", "Kingsland,GA", "Kingston,GA", "Kite,GA", "LaFayette,GA", "LaGrange,GA", "Lake City,GA", "Lakeland,GA", "Lake Park,GA", "Lavonia,GA", "Lawrenceville,GA", "Leary,GA", "Leesburg,GA", "Lenox,GA", "Leslie,GA", "Lexington,GA", "Lilburn,GA", "Lilly,GA", "Lincolnton,GA", "Lithonia,GA", "Locust Grove,GA", "Loganville,GA", "Lone Oak,GA", "Lookout Mountain,GA", "Louisville,GA", "Lovejoy,GA", "Ludowici,GA", "Lula,GA", "Lumber City,GA", "Lumpkin,GA", "Luthersville,GA", "Lyerly,GA", "Lyons,GA", "McCaysville,GA", "McDonough,GA", "McIntyre,GA", "Macon[j],GA", "McRae,GA", "Madison,GA", "Manassas,GA", "Manchester,GA", "Mansfield,GA", "Marietta,GA", "Marshallville,GA", "Martin,GA", "Maxeys,GA", "Maysville,GA", "Meansville,GA", "Meigs,GA", "Menlo,GA", "Metter,GA", "Midville,GA", "Midway,GA", "Milan,GA", "Milledgeville,GA", "Millen,GA", "Milner,GA", "Milton,GA", "Mitchell,GA", "Molena,GA", "Monroe,GA", "Montezuma,GA", "Monticello,GA", "Montrose,GA", "Moreland,GA", "Morgan,GA", "Morganton,GA", "Morrow,GA", "Morven,GA", "Moultrie,GA", "Mountain City,GA", "Mountain Park,GA", "Mount Airy,GA", "Mount Vernon,GA", "Mount Zion,GA", "Nahunta,GA", "Nashville,GA", "Nelson,GA", "Newborn,GA", "Newington,GA", "Newnan,GA", "Newton,GA", "Nicholls,GA", "Nicholson,GA", "Norcross,GA", "Norman Park,GA", "North High Shoals,GA", "Norwood,GA", "Nunez,GA", "Oak Park,GA", "Oakwood,GA", "Ochlocknee,GA", "Ocilla,GA", "Oconee,GA", "Odum,GA", "Offerman,GA", "Oglethorpe,GA", "Oliver,GA", "Omega,GA", "Orchard Hill,GA", "Oxford,GA", "Palmetto,GA", "Parrott,GA", "Patterson,GA", "Pavo,GA", "Peachtree City,GA", "Peachtree Corners[k],GA", "Pearson,GA", "Pelham,GA", "Pembroke,GA", "Pendergrass,GA", "Perry,GA", "Pinehurst,GA", "Pine Lake,GA", "Pine Mountain,GA", "Pineview,GA", "Pitts,GA", "Plains,GA", "Plainville,GA", "Pooler,GA", "Portal,GA", "Porterdale,GA", "Port Wentworth,GA", "Poulan,GA", "Powder Springs,GA", "Pulaski,GA", "Quitman,GA", "Ranger,GA", "Ray City,GA", "Rayle,GA", "Rebecca,GA", "Register,GA", "Reidsville,GA", "Remerton,GA", "Rentz,GA", "Resaca,GA", "Rest Haven,GA", "Reynolds,GA", "Rhine,GA", "Riceboro,GA", "Richland,GA", "Richmond Hill,GA", "Riddleville,GA", "Rincon,GA", "Ringgold,GA", "Riverdale,GA", "Riverside,GA", "Roberta,GA", "Rochelle,GA", "Rockmart,GA", "Rocky Ford,GA", "Rome,GA", "Roopville,GA", "Rossville,GA", "Roswell,GA", "Royston,GA", "Rutledge,GA", "St. Marys,GA", "Sale City,GA", "Sandersville,GA", "Sandy Springs,GA", "Santa Claus,GA", "Sardis,GA", "Sasser,GA", "Savannah,GA", "Scotland,GA", "Screven,GA", "Senoia,GA", "Shady Dale,GA", "Sharon,GA", "Sharpsburg,GA", "Shellman,GA", "Shiloh,GA", "Siloam,GA", "Sky Valley,GA", "Smithville,GA", "Smyrna,GA", "Snellville,GA", "Social Circle,GA", "Soperton,GA", "South Fulton,GA", "Sparks,GA", "Sparta,GA", "Springfield,GA", "Stapleton,GA", "Statesboro,GA", "Statham,GA", "Stillmore,GA", "Stockbridge,GA", "Stone Mountain,GA", "Stonecrest,GA", "Sugar Hill,GA", "Summertown,GA", "Summerville,GA", "Sumner,GA", "Sunny Side,GA", "Surrency,GA", "Suwanee,GA", "Swainsboro,GA", "Sycamore,GA", "Sylvania,GA", "Sylvester,GA", "Talbotton,GA", "Talking Rock,GA", "Tallapoosa,GA", "Tallulah Falls,GA", "Talmo,GA", "Tarrytown,GA", "Taylorsville,GA", "Temple,GA", "Tennille,GA", "Thomaston,GA", "Thomasville,GA", "Thomson,GA", "Thunderbolt,GA", "Tifton,GA", "Tiger,GA", "Tignall,GA", "Toccoa,GA", "Toomsboro,GA", "Trenton,GA", "Trion,GA", "Tunnel Hill,GA", "Turin,GA", "Twin City,GA", "Tybee Island,GA", "Tyrone,GA", "Ty Ty,GA", "Unadilla,GA", "Union City,GA", "Union Point,GA", "Uvalda,GA", "Valdosta,GA", "Varnell,GA", "Vernonburg,GA", "Vidalia,GA", "Vidette,GA", "Vienna,GA", "Villa Rica,GA", "Waco,GA", "Wadley,GA", "Waleska,GA", "Walnut Grove,GA", "Walthourville,GA", "Warm Springs,GA", "Warner Robins,GA", "Warrenton,GA", "Warwick,GA", "Washington,GA", "Watkinsville,GA", "Waverly Hall,GA", "Waycross,GA", "Waynesboro,GA", "Webster County[l],GA", "West Point,GA", "Whigham,GA", "White,GA", "White Plains,GA", "Whitesburg,GA", "Willacoochee,GA", "Williamson,GA", "Winder,GA", "Winterville,GA", "Woodbine,GA", "Woodbury,GA", "Woodland,GA", "Woodstock,GA", "Woodville,GA", "Woolsey,GA", "Wrens,GA", "Wrightsville,GA", "Yatesville,GA", "Young Harris,GA", "Zebulon,GA" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(OriginList);

        DetinationList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Abbeville,GA", "Acworth,GA", "Adairsville,GA", "Adel,GA", "Adrian,GA", "Ailey,GA", "Alamo,GA", "Alapaha,GA", "Albany,GA", "Aldora,GA", "Allenhurst,GA", "Allentown,GA", "Alma,GA", "Alpharetta,GA", "Alston,GA", "Alto,GA", "Ambrose,GA", "Americus,GA", "Andersonville,GA", "Arabi,GA", "Aragon,GA", "Arcade,GA", "Argyle,GA", "Arlington,GA", "Arnoldsville,GA", "Ashburn,GA", "Athens[b],GA", "Atlanta,GA", "Attapulgus,GA", "Auburn,GA", "Augusta[c],GA", "Austell,GA", "Avalon,GA", "Avera,GA", "Avondale Estates,GA", "Baconton,GA", "Bainbridge,GA", "Baldwin,GA", "Ball Ground,GA", "Barnesville,GA", "Bartow,GA", "Barwick,GA", "Baxley,GA", "Bellville,GA", "Berkeley Lake,GA", "Berlin,GA", "Bethlehem,GA", "Between,GA", "Bishop,GA", "Blackshear,GA", "Blairsville,GA", "Blakely,GA", "Bloomingdale,GA", "Blue Ridge,GA", "Bluffton,GA", "Blythe,GA", "Bogart,GA", "Boston,GA", "Bostwick,GA", "Bowdon,GA", "Bowersville,GA", "Bowman,GA", "Braselton,GA", "Braswell,GA", "Bremen,GA", "Brinson,GA", "Bronwood,GA", "Brookhaven[d],GA", "Brooklet,GA", "Brooks,GA", "Broxton,GA", "Brunswick,GA", "Buchanan,GA", "Buckhead,GA", "Buena Vista,GA", "Buford,GA", "Butler,GA", "Byromville,GA", "Byron,GA", "Cadwell,GA", "Cairo,GA", "Calhoun,GA", "Camak,GA", "Camilla,GA", "Canon,GA", "Canton,GA", "Carl,GA", "Carlton,GA", "Carnesville,GA", "Carrollton,GA", "Cartersville,GA", "Cave Spring,GA", "Cecil,GA", "Cedartown,GA", "Centerville,GA", "Centralhatchee,GA", "Chamblee,GA", "Chatsworth,GA", "Chattahoochee Hills[e],GA", "Chauncey,GA", "Chester,GA", "Chickamauga,GA", "Clarkesville,GA", "Clarkston,GA", "Claxton,GA", "Clayton,GA", "Clermont,GA", "Cleveland,GA", "Climax,GA", "Cobbtown,GA", "Cochran,GA", "Cohutta,GA", "Colbert,GA", "College Park,GA", "Collins,GA", "Colquitt,GA", "Columbus[f],GA", "Comer,GA", "Commerce,GA", "Concord,GA", "Conyers,GA", "Coolidge,GA", "Cordele,GA", "Cornelia,GA", "Covington,GA", "Crawford,GA", "Crawfordville,GA", "Culloden,GA", "Cumming,GA", "Cusseta[g],GA", "Cuthbert,GA", "Dacula,GA", "Dahlonega,GA", "Daisy,GA", "Dallas,GA", "Dalton,GA", "Damascus,GA", "Danielsville,GA", "Danville,GA", "Darien,GA", "Dasher,GA", "Davisboro,GA", "Dawson,GA", "Dawsonville,GA", "Dearing,GA", "Decatur,GA", "Deepstep,GA", "Demorest,GA", "Denton,GA", "De Soto,GA", "Dexter,GA", "Dillard,GA", "Doerun,GA", "Donalsonville,GA", "Dooling,GA", "Doraville,GA", "Douglas,GA", "Douglasville,GA", "Dublin,GA", "Dudley,GA", "Duluth,GA", "Dunwoody,GA", "Du Pont,GA", "East Dublin,GA", "East Ellijay,GA", "Eastman,GA", "East Point,GA", "Eatonton,GA", "Echols County[h],GA", "Edge Hill,GA", "Edison,GA", "Elberton,GA", "Ellaville,GA", "Ellenton,GA", "Ellijay,GA", "Emerson,GA", "Enigma,GA", "Ephesus,GA", "Eton,GA", "Euharlee,GA", "Fairburn,GA", "Fairmount,GA", "Fargo,GA", "Fayetteville,GA", "Fitzgerald,GA", "Flemington,GA", "Flovilla,GA", "Flowery Branch,GA", "Folkston,GA", "Forest Park,GA", "Forsyth,GA", "Fort Gaines,GA", "Fort Oglethorpe,GA", "Fort Valley,GA", "Franklin,GA", "Franklin Springs,GA", "Funston,GA", "Gainesville,GA", "Garden City,GA", "Garfield,GA", "Gay,GA", "Geneva,GA", "Georgetown,GA", "Gibson,GA", "Gillsville,GA", "Girard,GA", "Glennville,GA", "Glenwood,GA", "Good Hope,GA", "Gordon,GA", "Graham,GA", "Grantville,GA", "Gray,GA", "Grayson,GA", "Greensboro,GA", "Greenville,GA", "Griffin,GA", "Grovetown,GA", "Gumbranch,GA", "Guyton,GA", "Hagan,GA", "Hahira,GA", "Hamilton,GA", "Hampton,GA", "Hapeville,GA", "Haralson,GA", "Harlem,GA", "Harrison,GA", "Hartwell,GA", "Hawkinsville,GA", "Hazlehurst,GA", "Helen,GA", "Helena,GA", "Hephzibah,GA", "Hiawassee,GA", "Higgston,GA", "Hiltonia,GA", "Hinesville,GA", "Hiram,GA", "Hoboken,GA", "Hogansville,GA", "Holly Springs,GA", "Homeland,GA", "Homer,GA", "Homerville,GA", "Hoschton,GA", "Hull,GA", "Ideal,GA", "Ila,GA", "Iron City,GA", "Irwinton,GA", "Ivey,GA", "Jackson,GA", "Jacksonville,GA", "Jakin,GA", "Jasper,GA", "Jefferson,GA", "Jeffersonville,GA", "Jenkinsburg,GA", "Jersey,GA", "Jesup,GA", "Johns Creek [i],GA", "Jonesboro,GA", "Junction City,GA", "Kennesaw,GA", "Keysville,GA", "Kingsland,GA", "Kingston,GA", "Kite,GA", "LaFayette,GA", "LaGrange,GA", "Lake City,GA", "Lakeland,GA", "Lake Park,GA", "Lavonia,GA", "Lawrenceville,GA", "Leary,GA", "Leesburg,GA", "Lenox,GA", "Leslie,GA", "Lexington,GA", "Lilburn,GA", "Lilly,GA", "Lincolnton,GA", "Lithonia,GA", "Locust Grove,GA", "Loganville,GA", "Lone Oak,GA", "Lookout Mountain,GA", "Louisville,GA", "Lovejoy,GA", "Ludowici,GA", "Lula,GA", "Lumber City,GA", "Lumpkin,GA", "Luthersville,GA", "Lyerly,GA", "Lyons,GA", "McCaysville,GA", "McDonough,GA", "McIntyre,GA", "Macon[j],GA", "McRae,GA", "Madison,GA", "Manassas,GA", "Manchester,GA", "Mansfield,GA", "Marietta,GA", "Marshallville,GA", "Martin,GA", "Maxeys,GA", "Maysville,GA", "Meansville,GA", "Meigs,GA", "Menlo,GA", "Metter,GA", "Midville,GA", "Midway,GA", "Milan,GA", "Milledgeville,GA", "Millen,GA", "Milner,GA", "Milton,GA", "Mitchell,GA", "Molena,GA", "Monroe,GA", "Montezuma,GA", "Monticello,GA", "Montrose,GA", "Moreland,GA", "Morgan,GA", "Morganton,GA", "Morrow,GA", "Morven,GA", "Moultrie,GA", "Mountain City,GA", "Mountain Park,GA", "Mount Airy,GA", "Mount Vernon,GA", "Mount Zion,GA", "Nahunta,GA", "Nashville,GA", "Nelson,GA", "Newborn,GA", "Newington,GA", "Newnan,GA", "Newton,GA", "Nicholls,GA", "Nicholson,GA", "Norcross,GA", "Norman Park,GA", "North High Shoals,GA", "Norwood,GA", "Nunez,GA", "Oak Park,GA", "Oakwood,GA", "Ochlocknee,GA", "Ocilla,GA", "Oconee,GA", "Odum,GA", "Offerman,GA", "Oglethorpe,GA", "Oliver,GA", "Omega,GA", "Orchard Hill,GA", "Oxford,GA", "Palmetto,GA", "Parrott,GA", "Patterson,GA", "Pavo,GA", "Peachtree City,GA", "Peachtree Corners[k],GA", "Pearson,GA", "Pelham,GA", "Pembroke,GA", "Pendergrass,GA", "Perry,GA", "Pinehurst,GA", "Pine Lake,GA", "Pine Mountain,GA", "Pineview,GA", "Pitts,GA", "Plains,GA", "Plainville,GA", "Pooler,GA", "Portal,GA", "Porterdale,GA", "Port Wentworth,GA", "Poulan,GA", "Powder Springs,GA", "Pulaski,GA", "Quitman,GA", "Ranger,GA", "Ray City,GA", "Rayle,GA", "Rebecca,GA", "Register,GA", "Reidsville,GA", "Remerton,GA", "Rentz,GA", "Resaca,GA", "Rest Haven,GA", "Reynolds,GA", "Rhine,GA", "Riceboro,GA", "Richland,GA", "Richmond Hill,GA", "Riddleville,GA", "Rincon,GA", "Ringgold,GA", "Riverdale,GA", "Riverside,GA", "Roberta,GA", "Rochelle,GA", "Rockmart,GA", "Rocky Ford,GA", "Rome,GA", "Roopville,GA", "Rossville,GA", "Roswell,GA", "Royston,GA", "Rutledge,GA", "St. Marys,GA", "Sale City,GA", "Sandersville,GA", "Sandy Springs,GA", "Santa Claus,GA", "Sardis,GA", "Sasser,GA", "Savannah,GA", "Scotland,GA", "Screven,GA", "Senoia,GA", "Shady Dale,GA", "Sharon,GA", "Sharpsburg,GA", "Shellman,GA", "Shiloh,GA", "Siloam,GA", "Sky Valley,GA", "Smithville,GA", "Smyrna,GA", "Snellville,GA", "Social Circle,GA", "Soperton,GA", "South Fulton,GA", "Sparks,GA", "Sparta,GA", "Springfield,GA", "Stapleton,GA", "Statesboro,GA", "Statham,GA", "Stillmore,GA", "Stockbridge,GA", "Stone Mountain,GA", "Stonecrest,GA", "Sugar Hill,GA", "Summertown,GA", "Summerville,GA", "Sumner,GA", "Sunny Side,GA", "Surrency,GA", "Suwanee,GA", "Swainsboro,GA", "Sycamore,GA", "Sylvania,GA", "Sylvester,GA", "Talbotton,GA", "Talking Rock,GA", "Tallapoosa,GA", "Tallulah Falls,GA", "Talmo,GA", "Tarrytown,GA", "Taylorsville,GA", "Temple,GA", "Tennille,GA", "Thomaston,GA", "Thomasville,GA", "Thomson,GA", "Thunderbolt,GA", "Tifton,GA", "Tiger,GA", "Tignall,GA", "Toccoa,GA", "Toomsboro,GA", "Trenton,GA", "Trion,GA", "Tunnel Hill,GA", "Turin,GA", "Twin City,GA", "Tybee Island,GA", "Tyrone,GA", "Ty Ty,GA", "Unadilla,GA", "Union City,GA", "Union Point,GA", "Uvalda,GA", "Valdosta,GA", "Varnell,GA", "Vernonburg,GA", "Vidalia,GA", "Vidette,GA", "Vienna,GA", "Villa Rica,GA", "Waco,GA", "Wadley,GA", "Waleska,GA", "Walnut Grove,GA", "Walthourville,GA", "Warm Springs,GA", "Warner Robins,GA", "Warrenton,GA", "Warwick,GA", "Washington,GA", "Watkinsville,GA", "Waverly Hall,GA", "Waycross,GA", "Waynesboro,GA", "Webster County[l],GA", "West Point,GA", "Whigham,GA", "White,GA", "White Plains,GA", "Whitesburg,GA", "Willacoochee,GA", "Williamson,GA", "Winder,GA", "Winterville,GA", "Woodbine,GA", "Woodbury,GA", "Woodland,GA", "Woodstock,GA", "Woodville,GA", "Woolsey,GA", "Wrens,GA", "Wrightsville,GA", "Yatesville,GA", "Young Harris,GA", "Zebulon,GA" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(DetinationList);

        VolumeIn.setText("0");

        WeightIn.setText("0");

        jLabel8.setText("Origin");

        jLabel9.setText("Destination");

        jLabel11.setText("Volume");

        jLabel13.setText("Weight");

        jLabel15.setText("ORDER");

        addOrder.setText("ADD");
        addOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrderActionPerformed(evt);
            }
        });

        jLabel16.setText("Total Orders");

        totalOrders.setText("0");

        totalCities.setText("Total Cities");

        totalCitiesDis.setText("0");

        jLabel17.setText("Origen City");

        OriginCity.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Abbeville,GA", "Acworth,GA", "Adairsville,GA", "Adel,GA", "Adrian,GA", "Ailey,GA", "Alamo,GA", "Alapaha,GA", "Albany,GA", "Aldora,GA", "Allenhurst,GA", "Allentown,GA", "Alma,GA", "Alpharetta,GA", "Alston,GA", "Alto,GA", "Ambrose,GA", "Americus,GA", "Andersonville,GA", "Arabi,GA", "Aragon,GA", "Arcade,GA", "Argyle,GA", "Arlington,GA", "Arnoldsville,GA", "Ashburn,GA", "Athens[b],GA", "Atlanta,GA", "Attapulgus,GA", "Auburn,GA", "Augusta[c],GA", "Austell,GA", "Avalon,GA", "Avera,GA", "Avondale Estates,GA", "Baconton,GA", "Bainbridge,GA", "Baldwin,GA", "Ball Ground,GA", "Barnesville,GA", "Bartow,GA", "Barwick,GA", "Baxley,GA", "Bellville,GA", "Berkeley Lake,GA", "Berlin,GA", "Bethlehem,GA", "Between,GA", "Bishop,GA", "Blackshear,GA", "Blairsville,GA", "Blakely,GA", "Bloomingdale,GA", "Blue Ridge,GA", "Bluffton,GA", "Blythe,GA", "Bogart,GA", "Boston,GA", "Bostwick,GA", "Bowdon,GA", "Bowersville,GA", "Bowman,GA", "Braselton,GA", "Braswell,GA", "Bremen,GA", "Brinson,GA", "Bronwood,GA", "Brookhaven[d],GA", "Brooklet,GA", "Brooks,GA", "Broxton,GA", "Brunswick,GA", "Buchanan,GA", "Buckhead,GA", "Buena Vista,GA", "Buford,GA", "Butler,GA", "Byromville,GA", "Byron,GA", "Cadwell,GA", "Cairo,GA", "Calhoun,GA", "Camak,GA", "Camilla,GA", "Canon,GA", "Canton,GA", "Carl,GA", "Carlton,GA", "Carnesville,GA", "Carrollton,GA", "Cartersville,GA", "Cave Spring,GA", "Cecil,GA", "Cedartown,GA", "Centerville,GA", "Centralhatchee,GA", "Chamblee,GA", "Chatsworth,GA", "Chattahoochee Hills[e],GA", "Chauncey,GA", "Chester,GA", "Chickamauga,GA", "Clarkesville,GA", "Clarkston,GA", "Claxton,GA", "Clayton,GA", "Clermont,GA", "Cleveland,GA", "Climax,GA", "Cobbtown,GA", "Cochran,GA", "Cohutta,GA", "Colbert,GA", "College Park,GA", "Collins,GA", "Colquitt,GA", "Columbus[f],GA", "Comer,GA", "Commerce,GA", "Concord,GA", "Conyers,GA", "Coolidge,GA", "Cordele,GA", "Cornelia,GA", "Covington,GA", "Crawford,GA", "Crawfordville,GA", "Culloden,GA", "Cumming,GA", "Cusseta[g],GA", "Cuthbert,GA", "Dacula,GA", "Dahlonega,GA", "Daisy,GA", "Dallas,GA", "Dalton,GA", "Damascus,GA", "Danielsville,GA", "Danville,GA", "Darien,GA", "Dasher,GA", "Davisboro,GA", "Dawson,GA", "Dawsonville,GA", "Dearing,GA", "Decatur,GA", "Deepstep,GA", "Demorest,GA", "Denton,GA", "De Soto,GA", "Dexter,GA", "Dillard,GA", "Doerun,GA", "Donalsonville,GA", "Dooling,GA", "Doraville,GA", "Douglas,GA", "Douglasville,GA", "Dublin,GA", "Dudley,GA", "Duluth,GA", "Dunwoody,GA", "Du Pont,GA", "East Dublin,GA", "East Ellijay,GA", "Eastman,GA", "East Point,GA", "Eatonton,GA", "Echols County[h],GA", "Edge Hill,GA", "Edison,GA", "Elberton,GA", "Ellaville,GA", "Ellenton,GA", "Ellijay,GA", "Emerson,GA", "Enigma,GA", "Ephesus,GA", "Eton,GA", "Euharlee,GA", "Fairburn,GA", "Fairmount,GA", "Fargo,GA", "Fayetteville,GA", "Fitzgerald,GA", "Flemington,GA", "Flovilla,GA", "Flowery Branch,GA", "Folkston,GA", "Forest Park,GA", "Forsyth,GA", "Fort Gaines,GA", "Fort Oglethorpe,GA", "Fort Valley,GA", "Franklin,GA", "Franklin Springs,GA", "Funston,GA", "Gainesville,GA", "Garden City,GA", "Garfield,GA", "Gay,GA", "Geneva,GA", "Georgetown,GA", "Gibson,GA", "Gillsville,GA", "Girard,GA", "Glennville,GA", "Glenwood,GA", "Good Hope,GA", "Gordon,GA", "Graham,GA", "Grantville,GA", "Gray,GA", "Grayson,GA", "Greensboro,GA", "Greenville,GA", "Griffin,GA", "Grovetown,GA", "Gumbranch,GA", "Guyton,GA", "Hagan,GA", "Hahira,GA", "Hamilton,GA", "Hampton,GA", "Hapeville,GA", "Haralson,GA", "Harlem,GA", "Harrison,GA", "Hartwell,GA", "Hawkinsville,GA", "Hazlehurst,GA", "Helen,GA", "Helena,GA", "Hephzibah,GA", "Hiawassee,GA", "Higgston,GA", "Hiltonia,GA", "Hinesville,GA", "Hiram,GA", "Hoboken,GA", "Hogansville,GA", "Holly Springs,GA", "Homeland,GA", "Homer,GA", "Homerville,GA", "Hoschton,GA", "Hull,GA", "Ideal,GA", "Ila,GA", "Iron City,GA", "Irwinton,GA", "Ivey,GA", "Jackson,GA", "Jacksonville,GA", "Jakin,GA", "Jasper,GA", "Jefferson,GA", "Jeffersonville,GA", "Jenkinsburg,GA", "Jersey,GA", "Jesup,GA", "Johns Creek [i],GA", "Jonesboro,GA", "Junction City,GA", "Kennesaw,GA", "Keysville,GA", "Kingsland,GA", "Kingston,GA", "Kite,GA", "LaFayette,GA", "LaGrange,GA", "Lake City,GA", "Lakeland,GA", "Lake Park,GA", "Lavonia,GA", "Lawrenceville,GA", "Leary,GA", "Leesburg,GA", "Lenox,GA", "Leslie,GA", "Lexington,GA", "Lilburn,GA", "Lilly,GA", "Lincolnton,GA", "Lithonia,GA", "Locust Grove,GA", "Loganville,GA", "Lone Oak,GA", "Lookout Mountain,GA", "Louisville,GA", "Lovejoy,GA", "Ludowici,GA", "Lula,GA", "Lumber City,GA", "Lumpkin,GA", "Luthersville,GA", "Lyerly,GA", "Lyons,GA", "McCaysville,GA", "McDonough,GA", "McIntyre,GA", "Macon[j],GA", "McRae,GA", "Madison,GA", "Manassas,GA", "Manchester,GA", "Mansfield,GA", "Marietta,GA", "Marshallville,GA", "Martin,GA", "Maxeys,GA", "Maysville,GA", "Meansville,GA", "Meigs,GA", "Menlo,GA", "Metter,GA", "Midville,GA", "Midway,GA", "Milan,GA", "Milledgeville,GA", "Millen,GA", "Milner,GA", "Milton,GA", "Mitchell,GA", "Molena,GA", "Monroe,GA", "Montezuma,GA", "Monticello,GA", "Montrose,GA", "Moreland,GA", "Morgan,GA", "Morganton,GA", "Morrow,GA", "Morven,GA", "Moultrie,GA", "Mountain City,GA", "Mountain Park,GA", "Mount Airy,GA", "Mount Vernon,GA", "Mount Zion,GA", "Nahunta,GA", "Nashville,GA", "Nelson,GA", "Newborn,GA", "Newington,GA", "Newnan,GA", "Newton,GA", "Nicholls,GA", "Nicholson,GA", "Norcross,GA", "Norman Park,GA", "North High Shoals,GA", "Norwood,GA", "Nunez,GA", "Oak Park,GA", "Oakwood,GA", "Ochlocknee,GA", "Ocilla,GA", "Oconee,GA", "Odum,GA", "Offerman,GA", "Oglethorpe,GA", "Oliver,GA", "Omega,GA", "Orchard Hill,GA", "Oxford,GA", "Palmetto,GA", "Parrott,GA", "Patterson,GA", "Pavo,GA", "Peachtree City,GA", "Peachtree Corners[k],GA", "Pearson,GA", "Pelham,GA", "Pembroke,GA", "Pendergrass,GA", "Perry,GA", "Pinehurst,GA", "Pine Lake,GA", "Pine Mountain,GA", "Pineview,GA", "Pitts,GA", "Plains,GA", "Plainville,GA", "Pooler,GA", "Portal,GA", "Porterdale,GA", "Port Wentworth,GA", "Poulan,GA", "Powder Springs,GA", "Pulaski,GA", "Quitman,GA", "Ranger,GA", "Ray City,GA", "Rayle,GA", "Rebecca,GA", "Register,GA", "Reidsville,GA", "Remerton,GA", "Rentz,GA", "Resaca,GA", "Rest Haven,GA", "Reynolds,GA", "Rhine,GA", "Riceboro,GA", "Richland,GA", "Richmond Hill,GA", "Riddleville,GA", "Rincon,GA", "Ringgold,GA", "Riverdale,GA", "Riverside,GA", "Roberta,GA", "Rochelle,GA", "Rockmart,GA", "Rocky Ford,GA", "Rome,GA", "Roopville,GA", "Rossville,GA", "Roswell,GA", "Royston,GA", "Rutledge,GA", "St. Marys,GA", "Sale City,GA", "Sandersville,GA", "Sandy Springs,GA", "Santa Claus,GA", "Sardis,GA", "Sasser,GA", "Savannah,GA", "Scotland,GA", "Screven,GA", "Senoia,GA", "Shady Dale,GA", "Sharon,GA", "Sharpsburg,GA", "Shellman,GA", "Shiloh,GA", "Siloam,GA", "Sky Valley,GA", "Smithville,GA", "Smyrna,GA", "Snellville,GA", "Social Circle,GA", "Soperton,GA", "South Fulton,GA", "Sparks,GA", "Sparta,GA", "Springfield,GA", "Stapleton,GA", "Statesboro,GA", "Statham,GA", "Stillmore,GA", "Stockbridge,GA", "Stone Mountain,GA", "Stonecrest,GA", "Sugar Hill,GA", "Summertown,GA", "Summerville,GA", "Sumner,GA", "Sunny Side,GA", "Surrency,GA", "Suwanee,GA", "Swainsboro,GA", "Sycamore,GA", "Sylvania,GA", "Sylvester,GA", "Talbotton,GA", "Talking Rock,GA", "Tallapoosa,GA", "Tallulah Falls,GA", "Talmo,GA", "Tarrytown,GA", "Taylorsville,GA", "Temple,GA", "Tennille,GA", "Thomaston,GA", "Thomasville,GA", "Thomson,GA", "Thunderbolt,GA", "Tifton,GA", "Tiger,GA", "Tignall,GA", "Toccoa,GA", "Toomsboro,GA", "Trenton,GA", "Trion,GA", "Tunnel Hill,GA", "Turin,GA", "Twin City,GA", "Tybee Island,GA", "Tyrone,GA", "Ty Ty,GA", "Unadilla,GA", "Union City,GA", "Union Point,GA", "Uvalda,GA", "Valdosta,GA", "Varnell,GA", "Vernonburg,GA", "Vidalia,GA", "Vidette,GA", "Vienna,GA", "Villa Rica,GA", "Waco,GA", "Wadley,GA", "Waleska,GA", "Walnut Grove,GA", "Walthourville,GA", "Warm Springs,GA", "Warner Robins,GA", "Warrenton,GA", "Warwick,GA", "Washington,GA", "Watkinsville,GA", "Waverly Hall,GA", "Waycross,GA", "Waynesboro,GA", "Webster County[l],GA", "West Point,GA", "Whigham,GA", "White,GA", "White Plains,GA", "Whitesburg,GA", "Willacoochee,GA", "Williamson,GA", "Winder,GA", "Winterville,GA", "Woodbine,GA", "Woodbury,GA", "Woodland,GA", "Woodstock,GA", "Woodville,GA", "Woolsey,GA", "Wrens,GA", "Wrightsville,GA", "Yatesville,GA", "Young Harris,GA", "Zebulon,GA" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(OriginCity);

        addOrigen.setText("SET");
        addOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrigenActionPerformed(evt);
            }
        });

        mapShow.setText("SHOW");
        mapShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapShowActionPerformed(evt);
            }
        });

        jLabel2.setText("Map");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(380, 380, 380)
                                        .addComponent(jLabel15)
                                        .addGap(23, 23, 23))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel17)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel16))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(addOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(totalCities)))
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(WeightIn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalCitiesDis, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addComponent(jLabel5))
                                                .addGap(43, 43, 43)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField_FC, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField_K1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField_K2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField_PSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField_PGas, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(164, 164, 164)
                                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jButton_Load, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(VolumeIn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton_Run, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(90, 90, 90)
                                        .addComponent(jButton_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mapShow)
                                    .addComponent(jTextField_Profit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(234, 234, 234))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_FC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(jTextField_K1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_K2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_PSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel9))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(WeightIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton_Load)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel11)
                                                .addComponent(VolumeIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(addOrder)))
                                        .addGap(58, 58, 58)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel16)
                                            .addComponent(totalOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_PGas, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(totalCities)
                                            .addComponent(totalCitiesDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(addOrigen)))))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jTextField_Profit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jButton_Run)
                                .addComponent(jButton_Exit)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mapShow)
                    .addComponent(jLabel2))
                .addGap(3, 3, 3)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );

        jLabel17.getAccessibleContext().setAccessibleName("origenCity");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GetOrderSummary () {

        for (int i = 0; i < countOrders; i++) {
            
            if (myOrders.size() == 0) {
                OrderT currentOrder =  new OrderT();
                currentOrder.SetOrigen(ordersData.get(i)[0]);
                currentOrder.SetDestination(ordersData.get(i)[1]);
                currentOrder.insertData(i+1, Integer.parseInt(ordersData.get(i)[2]), Integer.parseInt(ordersData.get(i)[3]));
                myOrders.add(currentOrder);
            }else{
                String temOri = ordersData.get(i)[0];
                String temDest = ordersData.get(i)[1];
                int flagExist = 0;
                
                for (int j = 0; j < myOrders.size(); j++) {
                    if ((myOrders.get(j).GetOrigen() == temOri) && (myOrders.get(j).GetDestination() == temDest)){
                        myOrders.get(j).insertData(i+1, Integer.parseInt(ordersData.get(i)[2]), Integer.parseInt(ordersData.get(i)[3]));
                        flagExist =1;
                        break;
                    }
                }
                if(flagExist==0){
                    OrderT currentOrder =  new OrderT();
                    currentOrder.SetOrigen(ordersData.get(i)[0]);
                    currentOrder.SetDestination(ordersData.get(i)[1]);
                    currentOrder.insertData(i+1, Integer.parseInt(ordersData.get(i)[2]), Integer.parseInt(ordersData.get(i)[3]));
                    myOrders.add(currentOrder);
                }
            }
        }
        
        for (int k = 0; k < myOrders.size(); k++){
            orignesU.add(myOrders.get(k).GetOrigen()); 
        }
        for (int k = 0; k < myOrders.size(); k++){
            destinationsU.add(myOrders.get(k).GetDestination()); 
        }
        
        if(destinationsU.size()>1){
            System.out.println("WARNING... More than one destination");
            System.exit(1);
        }
 
        if (OrigenCity == ""){
            System.out.println("WARNING... Not Origen City");
            System.exit(1);
        }
        
        int flagOrigen = 0;
        for (String s : orignesU) {
            if (OrigenCity.equals(s)){
                flagOrigen = 1;
            } 
        }
        if (flagOrigen == 0){
            System.out.println("WARNING... Origen City should be one of the origen orders");
            System.exit(1);
        }
        
        allCitiesList.add(OrigenCity); 
        for (String s : orignesU) {
            if(!allCitiesList.contains(s)){
                allCitiesList.add(s); 
            }
        }
        
        for (String s : destinationsU) {
            allCitiesList.add(s); 
        }
        
        totalCitiesDis.setText(Integer.toString(allCitiesList.size()));

    }
    
    private String getStringRequest(int ind){
        String requestString = "";
        for (int i = 0; i < allCitiesList.size(); i++){
            if(i > ind){
                requestString = requestString + allCitiesList.get(i) + "|";
            }
        }
        requestString = requestString.substring(0, requestString.length() - 1);
        return requestString;
    }
    
    private void processRequest(int ind, String reqData){
        
        //System.out.println(reqData);
        JsonElement rootMatrix = new JsonParser().parse(reqData);
        JsonObject rootMatrixJson = rootMatrix.getAsJsonObject();
        JsonArray elements = rootMatrixJson.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("elements");
        
        int postem = 0;
        
        for (int i = 0; i < NCities; i++){
            if(ind == i){
                distancesIni[i][i] = 0;
            }else if(i > ind){
                //System.out.println(Integer.parseInt(elements.get(postem).getAsJsonObject().get("distance").getAsJsonObject().get("value").getAsString()));
                distancesIni[ind][i] = (int) ((Integer.parseInt(elements.get(postem).getAsJsonObject().get("distance").getAsJsonObject().get("value").getAsString()))/1609.34);
                postem++;
            }
        }
        
        postem = 0;
        for (int i = 0; i < NCities; i++){
            if(ind == i){
                times[i][i] = 0.001;
            }else if(i > ind){
                times[ind][i] = (Double.parseDouble(elements.get(postem).getAsJsonObject().get("duration").getAsJsonObject().get("value").getAsString()))/3600;
                postem++;
            }
        }
    }
    
    private String GetDataGoogleMaps (String oriR, String destR) throws IOException{

        //System.out.println("\nGET MAPS DATA");
        
        String jsonMatrix = "";
        

        HttpURLConnection con = null;

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + oriR + "&destinations=" + destR + "&departure_time=now&key=" + privateKey;

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            jsonMatrix = content.toString();

        } finally {
            
            con.disconnect();
        }
        
        return jsonMatrix;
    }
    
    private void initMatrixData(){
        
        NCities = allCitiesList.size();
        Cities = new String [NCities];
        distancesIni = new int [NCities][NCities];
        times = new double [NCities][NCities];
        speeds = new int [NCities][NCities];
        distances = new int [NCities][NCities];
        
        //System.out.println("List of Cities");
        for (int i = 0; i < allCitiesList.size(); i++){
            Cities[i] = allCitiesList.get(i);
            //System.out.println(allCitiesList.get(i));
        }
        
        String origenRequest = "";
        String destinationRequest = "";
        String resultRequest = "";
        
        
        for (int i = 0; i < allCitiesList.size()-1; i++){
            origenRequest = allCitiesList.get(i);
            destinationRequest = getStringRequest(i);
            //System.out.println(origenRequest);
            //System.out.println(destinationRequest);
            
            try {
                resultRequest = GetDataGoogleMaps(origenRequest, destinationRequest);
            } catch (IOException ex) {
                Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            processRequest(i, resultRequest);
        }
        
        distancesIni[NCities-1][NCities-1] = 0;
        times[NCities-1][NCities-1] = 0.001;
                
        //Replicating Half Upper Matrix to Half Lower Matrix
        for (int i = 0; i < NCities; i++){for (int j = 0; j < NCities; j++){if (i > j){distancesIni[i][j] = distancesIni[j][i];}}}
        for (int i = 0; i < NCities; i++){for (int j = 0; j < NCities; j++){if (i > j){times[i][j] = times[j][i];}}}
        
        //Calculating Speeds
        for (int i = 0; i < NCities; i++){for (int j = 0; j < NCities; j++){speeds[i][j]= (int) (distancesIni[i][j]/times[i][j]);}}
        
        
        //Calculating Cost per edge
        int tempValCost = 0;
        for (int i = 0; i < NCities; i++) {
	    for (int j = 0; j < NCities; j++) {
                tempValCost =(int)((PGas+K2+(PSalary/speeds[i][j]))*distancesIni[i][j]);
                if(tempValCost>1000000){distances[i][j] = 1000000;}else{distances[i][j] = tempValCost;}
            }
	}
        
        //Printing for debug
//        System.out.println("Printing Cities");
//        for (int i = 0; i < NCities; i++){System.out.println(Cities[i]);}
//        System.out.println("Printing distancesIni");
//        for (int i = 0; i < NCities; i++){for (int j = 0; j < NCities; j++){System.out.print(distancesIni[i][j] + ",");}System.out.println("*");}
//        System.out.println("Printing times");
//        for (int i = 0; i < NCities; i++){for (int j = 0; j < NCities; j++){System.out.print(times[i][j] + ",");}System.out.println("*");}
//        System.out.println("Printing speeds");
//        for (int i = 0; i < NCities; i++){for (int j = 0; j < NCities; j++){System.out.print(speeds[i][j] + ",");}System.out.println("*");}
//        System.out.println("Printing cost as distances");
//        for (int i = 0; i < NCities; i++){for (int j = 0; j < NCities; j++){System.out.print(distances[i][j] + ",");}System.out.println("*");}

    }
    
    private void shortestPath(){
        
        Graph<Integer> graph = new Graph<>(false);

        for (int i = 0; i < NCities; i++) {
	    for (int j = i+1; j < NCities; j++) {
                graph.addEdge(i+1, j+1, distances[i][j]);
            }
	}
        
        DijkstraShortestPath dsp = new DijkstraShortestPath();
        
        File fout = new File(PathShortestDistanceFile);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fout);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)); 
        for (int i = 0; i < NCities; i++) {
	    Vertex<Integer> sourceVertex = graph.getVertex(i+1);
            Map<Vertex<Integer>,Integer> distance = dsp.shortestPath(graph, sourceVertex);
            String Temp = distance.toString();
            
            Temp = Temp.replace("{", "");
            Temp = Temp.replace("}", "");
            Temp = Temp.replaceAll(",\\s\\d*=", ",");
            Temp = Temp.replaceAll("^.*=", "");
            //System.out.println(Temp);
                       
            try {
                bw.write(Temp);
            } catch (IOException ex) {
                Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        try {  
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ////////////////////////////
        //Reading Calculated Shorted Distances
        ShortDistances = new int [NCities][NCities];
        File file3 = new File(PathShortestDistanceFile);
     
    	BufferedReader bufRdr3 = null;
        try {
            bufRdr3 = new BufferedReader(new FileReader(file3));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    	String line3 = null;
    	int row3 = 0;
    	int col3 = 0;
     
        try {
            //read each line of text file
            while((line3 = bufRdr3.readLine()) != null && row3<NCities)
            {
                StringTokenizer st3 = new StringTokenizer(line3,",");
                while (st3.hasMoreTokens())
                {
                    //get next token and store it in the array
                    ShortDistances[row3][col3] = Integer.parseInt(st3.nextToken());
                    col3++;
                }
                col3 = 0;
                row3++;
            }
        } catch (IOException ex) {
            Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void initClientsData(){
        
        NClients = NCities-1;
        
        //Reading Client Data
        ClientData = new int [NClients][3];
        
        for (int i = 0; i < Cities.length-1; i++){
            ClientData[i][0] = i;
            ClientData[i][1] = 0;
            ClientData[i][2] = 0;
            for(int j = 0; j < Cities.length-1; j++){
                if(Cities[i].equals(myOrders.get(j).GetOrigen())){
                    ClientData[i][1] = myOrders.get(j).GetWeightTotal();
                    ClientData[i][2] = myOrders.get(j).GetVolumeTotal();
                    break;
                }
            }
        }
        
        //Extraction Short Distances to just Clients
        ShortDistancesClients = new int [NClients+2][NClients+2];
        int parseRow=0;
        int parseCol=0;
        for (int i = 0; i < NClients+1; i++)
        {
            if(i==NClients)
            {
                parseRow=NCities-1;
            }else{
                parseRow = ClientData[i][0];
            }
            
            for (int j = 0; j < NClients+1; j++)
            {
                if(j==NClients)
                {
                    parseCol=NCities-1;
                }else{
                    parseCol = ClientData[j][0];
                }
                ShortDistancesClients[i][j] = ShortDistances[parseRow][parseCol];
            }
        }
        
        //Getting Alpha and Betha
        int tempTD =0;
        for (int i = 0; i < NClients; i++)
        {
            for (int j = 0; j < NClients; j++)
            {
                tempTD = tempTD+ShortDistances[i][j];
            }
        }
        Betha=tempTD;
        Alpha = 1000*Betha;
        
        ShortDistancesClients[0][NClients+1]=Betha;
        ShortDistancesClients[NClients+1][0]=Betha;
        ShortDistancesClients[NClients][NClients+1]=Betha;
        ShortDistancesClients[NClients+1][NClients]=Betha;
        ShortDistancesClients[NClients+1][NClients+1]=0;
        for (int i = 1; i < NClients; i++) {
                ShortDistancesClients[i][NClients+1]=Alpha;
    	}
        for (int i = 1; i < NClients; i++) {
                ShortDistancesClients[NClients+1][i]=Alpha;
    	}
        
        //Write to file 
        String TempVal=null;
        File fout2 = new File(PathShortestDistanceClientsFile);
        FileOutputStream fos2 = null;
        try {
            fos2 = new FileOutputStream(fout2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2)); 
        
        for (int i = 0; i < NClients+2; i++)
        {
            TempVal=null;
            for (int j = 0; j < NClients+2; j++)
            {
                TempVal = TempVal + "," + Integer.toString(ShortDistancesClients [i][j]);
            }
            //TempVal = TempVal + "\n";
            try {
                bw2.write(TempVal);
            } catch (IOException ex) {
                Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bw2.newLine();
            } catch (IOException ex) {
                Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {  
            bw2.close();
        } catch (IOException ex) {
            Logger.getLogger(DATAGUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void runTSP(){
        
        //Runing TSP with Held Karp
        TravelingSalesmanHeldKarp ht = new TravelingSalesmanHeldKarp();
        minCost = ht.minCost(ShortDistancesClients);
        minCost = minCost-(2*Betha);
        Tour = ht.getTour();
        System.out.println("\nTSP tour");
        Tour = Tour.replace("->", ",");
        System.out.println(Tour);
        System.out.println("Min cost is " + minCost);
        
        
        ClientsInOrder = new int [NClients+1];
        StringTokenizer st4 = new StringTokenizer(Tour,",");
        int row4 = 0;
        String TVal=null;
        while (st4.hasMoreTokens())
        {
            TVal = st4.nextToken();
            if(row4!=0 && row4!=1)
            {
                ClientsInOrder[row4-2] = Integer.parseInt(TVal);
                //System.out.println(ClientsInOrder[row4-2]);
            }  
            row4++;
        }
        
        //Reverse array
        int left = 0;
        int right = ClientsInOrder.length - 1;

        while( left < right ) 
        {
            // swap the values at the left and right indices
            int temp = ClientsInOrder[left];
            ClientsInOrder[left] = ClientsInOrder[right];
            ClientsInOrder[right] = temp;

            // move the left and right index pointers in toward the center
            left++;
            right--;
        }
        
        //Mapping Names of Cities to visit
//        String CitiesToVisit = "";
//        for (int i = 0; i < NClients; i++)
//        {
//            if(i == 0){
//                CitiesToVisit = Cities[ClientData[ClientsInOrder[i]][0]];
//            }else{
//                CitiesToVisit = CitiesToVisit + " =>" + "\n" + Cities[ClientData[ClientsInOrder[i]][0]];
//            }
//        }
//        CitiesToVisit = CitiesToVisit + " =>" + "\n" + Cities[NCities-1];

        //jTextArea_Out.setText(CitiesToVisit);
        
        URLToVisit = "https://www.google.com/maps/dir/";
        //https://www.google.com/maps/dir/Macon,+Georgia/Atlanta,+Georgia/
        for (int i = 0; i < NClients; i++)
        {
            if(i == 0){
                URLToVisit = URLToVisit + Cities[ClientData[ClientsInOrder[i]][0]];
            }else{
                URLToVisit = URLToVisit + "/" + Cities[ClientData[ClientsInOrder[i]][0]];
            }
        }
        URLToVisit = URLToVisit + "/" + Cities[NCities-1];
        System.out.println(URLToVisit);
        //jTextArea_Out.setText(URLToVisit);
    }
    
    private void calCostRevenue(){
        
        //Getting Distances in order
        DistancesInOrder = new int [NClients];
        for (int i = 0; i < NClients; i++)
        {
            DistancesInOrder[i]=ShortDistancesClients[ClientsInOrder[i]][ClientsInOrder[i+1]];
        }
        
        //Checking
        int TempSumCost=0;
        for (int i = 0; i < NClients; i++)
        {
            TempSumCost = TempSumCost + DistancesInOrder[i];
        }
        System.out.println("Minimun weight: " + TempSumCost);
        
        //Calculating Total Cost
        TCost=FC+minCost;
        
        //Calculating Revenue
        for (int i = 0; i < NClients; i++)
        {
            Revenue = Revenue + ClientData[i][1]*ClientData[i][2]*ShortDistancesClients[i][NClients];
        }
        Revenue=K1*Revenue;
        System.out.println("Total Revenue: " + Revenue);
        
        
        Profit = Revenue - TCost;
        System.out.println("Total Profit: " + Profit);
        jTextField_Profit.setText(Double.toString(Profit));
        
    }
    
    private void printOrders(){
        
        
        //System.out.println("\n\nORDES DETAILS: \n");       
        for (int i = 0; i < NClients; i++)
        {
            for(OrderT or: myOrders){
                if(Cities[ClientData[ClientsInOrder[i]][0]] == or.GetOrigen()){
                    //System.out.println(or.getStringOrder() + "\n");
                    ordersSumary = ordersSumary + or.getStringOrder() + "\n\n";
                    break;
                }
            }
        }
        jTextArea_Out.setText(ordersSumary);
    }
    
    private void StartBrowser() {

	String url = URLToVisit;
	String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
	
	try{

	    if (os.indexOf( "win" ) >= 0) {

	        // this doesn't support showing urls in the form of "page.html#nameLink" 
	        rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);

	    } else if (os.indexOf( "mac" ) >= 0) {

	        rt.exec( "open " + url);

            } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {

	        // Do a best guess on unix until we get a platform independent way
	        // Build a list of browsers to try, in this order.
	        String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror", "netscape","opera","links","lynx"};
	        	
	        // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
	        StringBuffer cmd = new StringBuffer();
	        for (int i=0; i<browsers.length; i++)
	            cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
	        	
	        rt.exec(new String[] { "sh", "-c", cmd.toString() });

           }
       }catch (Exception e){
	    System.out.println(e);
       }
    }
    
    private void jButton_LoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadActionPerformed
        // TODO add your handling code here:

        FC = Double.parseDouble(jTextField_FC.getText());
        K1 = Double.parseDouble(jTextField_K1.getText());
        K2 = Double.parseDouble(jTextField_K2.getText());
        PGas = Double.parseDouble(jTextField_PGas.getText());
        PSalary = Double.parseDouble(jTextField_PSalary.getText());
   
    }//GEN-LAST:event_jButton_LoadActionPerformed

    private void jButton_RunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RunActionPerformed
        // TODO add your handling code here:
        
        java.util.Date date= new java.util.Date();
	    System.out.println(new Timestamp(date.getTime()));
        System.out.println(rootPath);
        
        GetOrderSummary();
        initMatrixData();
        shortestPath();
        initClientsData();
        runTSP();
        calCostRevenue();
        printOrders();
        
 
        java.util.Date date2= new java.util.Date();
        System.out.println(new Timestamp(date2.getTime()));
        
    }//GEN-LAST:event_jButton_RunActionPerformed
      
    private void jTextField_ProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ProfitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ProfitActionPerformed

    private void jButton_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton_ExitActionPerformed

    private void addOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrderActionPerformed
        // TODO add your handling code here:
        
        String[] tempString= {OriginList.getSelectedValue(),DetinationList.getSelectedValue(),WeightIn.getText(),VolumeIn.getText()};  
        ordersData.add(tempString);
        
        countOrders++;
        
        totalOrders.setText(Integer.toString(countOrders));

    }//GEN-LAST:event_addOrderActionPerformed

    private void addOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrigenActionPerformed
        // TODO add your handling code here:
        OrigenCity = OriginCity.getSelectedValue();
    }//GEN-LAST:event_addOrigenActionPerformed

    private void mapShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapShowActionPerformed
        // TODO add your handling code here:
        StartBrowser();
    }//GEN-LAST:event_mapShowActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DATAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DATAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DATAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DATAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DATAGUI().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> DetinationList;
    private javax.swing.JList<String> OriginCity;
    private javax.swing.JList<String> OriginList;
    private javax.swing.JTextField VolumeIn;
    private javax.swing.JTextField WeightIn;
    private javax.swing.JButton addOrder;
    private javax.swing.JButton addOrigen;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton_Exit;
    private javax.swing.JButton jButton_Load;
    private javax.swing.JButton jButton_Run;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea_Out;
    private javax.swing.JTextField jTextField_FC;
    private javax.swing.JTextField jTextField_K1;
    private javax.swing.JTextField jTextField_K2;
    private javax.swing.JTextField jTextField_PGas;
    private javax.swing.JTextField jTextField_PSalary;
    private javax.swing.JTextField jTextField_Profit;
    private javax.swing.JButton mapShow;
    private javax.swing.JLabel totalCities;
    private javax.swing.JTextField totalCitiesDis;
    private javax.swing.JTextField totalOrders;
    // End of variables declaration//GEN-END:variables
}
