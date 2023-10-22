
public final class ArchiveComplete_TEAM2 extends Archive_TEAM2 {
    private String departement;

    public ArchiveComplete_TEAM2(String nomorBerkas, String kodeKlasifikasi, String jenisArsip, int tahun, int jumlahBerkas, String tingkatPerkembangan, String nomorBoks, String departement) {
        super(nomorBerkas, kodeKlasifikasi, jenisArsip, tahun, jumlahBerkas, tingkatPerkembangan, nomorBoks);
        setDepartement(departement);
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String nDepartement) {
        departement = nDepartement;
    }

    @Override
    public void showArchive() {
        getNomorBerkas();
        getKodeKlasifikasi();
        getJenisArsip();
        getTahun();
        getJumlahBerkas();
        getTingkatPerkembangan();
        getNomorBoks();
        getDepartement();
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                "\nNomor Berkas: " + getNomorBerkas() +
                "\nKode Klasifikasi: " + getKodeKlasifikasi() +
                "\nJenis/Series Arsip: " + getJenisArsip() +
                "\nTahun: " + getTahun() +
                "\nJumlah Berkas: " + getJumlahBerkas() +
                "\nTingkat Perkembangan: " + getTingkatPerkembangan() +
                "\nNomor Boks: " + getNomorBoks() +
                "\nDepartemen: " + departement +
                "\n";
    }
}
