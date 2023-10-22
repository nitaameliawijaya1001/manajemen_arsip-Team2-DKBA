public class Archive_TEAM2 {
    private int id;
    private String nomorBerkas;
    private String kodeKlasifikasi;
    private String jenisArsip;
    private int tahun;
    private int jumlahBerkas;
    private String tingkatPerkembangan;
    private String nomorBoks;

    public Archive_TEAM2(String nomorBerkas, String kodeKlasifikasi, String jenisArsip, int tahun, int jumlahBerkas, String tingkatPerkembangan, String nomorBoks) {
        setNomorBerkas(nomorBerkas);
        setKodeKlasifikasi(kodeKlasifikasi);
        setJenisArsip(jenisArsip);
        setTahun(tahun);
        setJumlahBerkas(jumlahBerkas);
        setTingkatPerkembangan(tingkatPerkembangan);
        setNomorBoks(nomorBoks);
    }

    public int getId(){ return id; }
    public String getNomorBerkas() {
        return nomorBerkas;
    }

    public String getKodeKlasifikasi() {
        return kodeKlasifikasi;
    }

    public String getJenisArsip() {
        return jenisArsip;
    }

    public int getTahun() {
        return tahun;
    }

    public int getJumlahBerkas() {
        return jumlahBerkas;
    }

    public String getTingkatPerkembangan() {
        return tingkatPerkembangan;
    }

    public String getNomorBoks() {
        return nomorBoks;
    }

    public void setNomorBerkas(String nNomorBerkas) {
        nomorBerkas = nNomorBerkas;
    }

    public void setKodeKlasifikasi(String nKodeKlasifikasi) {
        kodeKlasifikasi = nKodeKlasifikasi;
    }

    public void setJenisArsip(String nJenisArsip) {
        jenisArsip = nJenisArsip;
    }

    public void setTahun(int nTahun) {
        tahun = nTahun;
    }

    public void setJumlahBerkas(int nJumlahBerkas) {
        jumlahBerkas = nJumlahBerkas;
    }

    public void setTingkatPerkembangan(String nTingkatPerkembangan) {
        tingkatPerkembangan = nTingkatPerkembangan;
    }

    public void setNomorBoks(String nNomorBoks) {
        nomorBoks = nNomorBoks;
    }

    public void showArchive() {
        getNomorBerkas();
        getKodeKlasifikasi();
        getJenisArsip();
        getTahun();
        getJumlahBerkas();
        getTingkatPerkembangan();
        getNomorBoks();
    }

}