import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_TEAM2 {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            createTableIfNotExists(connection);

            Scanner scanner = new Scanner(System.in);

            Boolean apps = true;

            while (apps) {
                System.out.println("Archive Management Program");
                System.out.println("1. Add Archive");
                System.out.println("2. View All Archives");
                System.out.println("3. Delete Archives");
                System.out.println("4. Total Archives");
                System.out.println("5. Search");
                System.out.println("6. Exit Program");
                System.out.print("Choose an option (1/2/3/4/5/6): ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> menuAddArchive(scanner, connection);
                    case 2 -> menuShowArchive(connection);
                    case 3 -> menuDelete(scanner, connection);
                    case 4 -> menuCountArchive(connection);
                    case 5 -> menuSearch(scanner, connection);
                    case 6 -> apps=false;
                    default -> System.out.println("Invalid option. Please choose again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableIfNotExists(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS archives (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nomorBerkas varchar(20)," +
                "kodeKlasifikasi varchar(10)," +
                "jenisArsip varchar(30)," +
                "tahun int," +
                "jumlahBerkas int," +
                "tingkatPerkembangan varchar(20)," +
                "nomorBoks varchar(20)," +
                "departemen varchar(50)" +
                ")";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    private static void addArchive(Connection connection, ArchiveComplete_TEAM2 nArchive) throws SQLException {
        String insertSQL = "INSERT INTO archives (nomorBerkas, kodeKlasifikasi, jenisArsip, tahun, jumlahBerkas, tingkatPerkembangan, nomorBoks, departemen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, nArchive.getNomorBerkas());
            statement.setString(2, nArchive.getKodeKlasifikasi());
            statement.setString(3, nArchive.getJenisArsip());
            statement.setInt(4, nArchive.getTahun());
            statement.setInt(5, nArchive.getJumlahBerkas());
            statement.setString(6, nArchive.getTingkatPerkembangan());
            statement.setString(7, nArchive.getNomorBoks());
            statement.setString(8, nArchive.getDepartement());
            statement.executeUpdate();
        }
    }

    private static List<ArchiveComplete_TEAM2> getAllArchives(Connection connection) throws SQLException {
        List<ArchiveComplete_TEAM2> archives = new ArrayList<>();
        String selectSQL = "SELECT nomorBerkas, kodeKlasifikasi, jenisArsip, tahun, jumlahBerkas, tingkatPerkembangan, nomorBoks, departemen FROM archives";
        try (PreparedStatement statement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String nomorBerkas = resultSet.getString("nomorBerkas");
                String kodeKlasifikasi = resultSet.getString("kodeKlasifikasi");
                String jenisArsip = resultSet.getString("jenisArsip");
                int tahun = resultSet.getInt("tahun");
                int jumlahBerkas = resultSet.getInt("jumlahBerkas");
                String tingkatPerkembangan = resultSet.getString("tingkatPerkembangan");
                String nomorBoks = resultSet.getString("nomorBoks");
                String departemen = resultSet.getString("departemen");
                archives.add(new ArchiveComplete_TEAM2(nomorBerkas, kodeKlasifikasi, jenisArsip, tahun, jumlahBerkas, tingkatPerkembangan, nomorBoks, departemen));
            }
        }
        return archives;
    }

    private static void delete(Connection connection, String nomorBerkas) throws SQLException {
        String deleteSQL = "DELETE FROM archives WHERE nomorBerkas =?";
        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setString(1, nomorBerkas);
            statement.executeUpdate();
        }
        System.out.println("Arsip berhasil dihapus!");
        System.out.println("============================");
    }

    private static int count(Connection connection) throws SQLException {
        String countSQL = "Select count(*) as total FROM archives";
        int total=0;
        try (PreparedStatement statement = connection.prepareStatement(countSQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        }
        return total;
    }

    public static List<ArchiveComplete_TEAM2> filterArchives(List<ArchiveComplete_TEAM2> archives, ArchiveFilter_TEAM2 filter) {
        List<ArchiveComplete_TEAM2> filteredArchives = new ArrayList<>();
        for (ArchiveComplete_TEAM2 archive : archives) {
            if (filter.filter(archive)) {
                filteredArchives.add(archive);
            }
        }
        return filteredArchives;
    }

    private static void menuAddArchive(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Masukan nomor berkas: ");
        String nomorBerkas = scanner.nextLine();
        System.out.print("Masukan kode klasifikasi: ");
        String kodeKlasifikasi = scanner.nextLine();
        System.out.print("Masukan kode jenis arsip: ");
        String jenisArsip = scanner.nextLine();
        System.out.print("Masukan departement: ");
        String departemen = scanner.nextLine();
        System.out.print("Masukan nomor boks: ");
        String nomorBoks = scanner.nextLine();
        System.out.print("Masukan tingkat perkembangan: ");
        String tingkatPerkembangan = scanner.nextLine();
        System.out.print("Masukan kode tahun: ");
        int tahun = scanner.nextInt();
        System.out.print("Masukan jumlah berkas: ");
        int jumlahBerkas = scanner.nextInt();
        System.out.println();
        ArchiveComplete_TEAM2 new_archive = new ArchiveComplete_TEAM2(nomorBerkas,kodeKlasifikasi,jenisArsip,tahun,jumlahBerkas,tingkatPerkembangan,nomorBoks,departemen);
        addArchive(connection, new_archive);
        System.out.println("Arsip berhasil diinput.");
    }

    private static void menuShowArchive(Connection connection) throws SQLException {
        List<ArchiveComplete_TEAM2> archives = getAllArchives(connection);
        archives.forEach(System.out::println);
    }

    private static void menuDelete(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("Masukan Nomor Berkas : ");
        String title_delete = scanner.nextLine();
        delete(connection, title_delete);
        List<ArchiveComplete_TEAM2> archives_2 = getAllArchives(connection);
        archives_2.forEach(System.out::println);
    }

    private static void menuCountArchive(Connection connection) throws SQLException {
        System.out.print("Total Arsip saat ini : ");
        int total = count(connection);
        System.out.println(total);
    }

    private static void menuSearch(Scanner scanner, Connection connection) throws SQLException {
        System.out.println("Cari Arsip berdasarkan Nomor Berkas : ");
        String key = scanner.nextLine();
        List<ArchiveComplete_TEAM2> archiveList = getAllArchives(connection);
        List<ArchiveComplete_TEAM2> filteredArchives = filterArchives(archiveList,
                archive -> archive.getNomorBerkas().contains(key));
        filteredArchives.forEach(System.out::println);
    }
}
