import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class ATMProgram {
    private static Map<String, String> userDatabase = new HashMap<>(); // Penyimpanan untuk ID pengguna dan password

    public static void main(String[] args) {

        // Menambahkan user pertama (untuk testing)
        userDatabase.put("user1", "password123");

        // Membuat Frame Utama
        JFrame frame = new JFrame("BANK PLECIT");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel Login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        loginPanel.setBackground(Color.CYAN); // Mengatur warna latar belakang panel login

        JLabel labelLogin = new JLabel("Login");
        JLabel labelUserId = new JLabel("User ID:");
        JLabel labelPassword = new JLabel("Password:");

        JTextField fieldUserId = new JTextField();
        JPasswordField fieldPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");
        JButton btnSignUp = new JButton("Sign Up");

        labelLogin.setForeground(Color.MAGENTA); // Mengatur warna teks untuk login label
        labelUserId.setForeground(Color.MAGENTA);
        labelPassword.setForeground(Color.MAGENTA);

        loginPanel.add(labelLogin);
        loginPanel.add(new JLabel()); // Kosong untuk padding
        loginPanel.add(labelUserId);
        loginPanel.add(fieldUserId);
        loginPanel.add(labelPassword);
        loginPanel.add(fieldPassword);

        // Panel untuk Tombol Login dan Sign Up
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Menggunakan FlowLayout untuk menata tombol secara horizontal
        buttonPanel.setBackground(Color.CYAN);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnSignUp);

        // Panel Utama untuk Input dan Tombol
        JPanel panelInput = new JPanel(new GridLayout(6, 4, 10, 10));
        panelInput.setBackground(new Color(72, 176, 232)); // Mengatur warna latar belakang panel input

        // Komponen Input dan Label
        JLabel labelNama = new JLabel("Nama Pengguna:");
        JTextField fieldNama = new JTextField();

        JLabel labelRekening = new JLabel("Rekening pengguna:");
        JTextField fieldRekening = new JTextField();

        JLabel labelSaldo = new JLabel("Data saldo:");
        JTextField fieldSaldo = new JTextField();

        JButton btnSubmit = new JButton("Selesai");
        btnSubmit.setBackground(new Color(255, 255, 255)); // Warna tombol
        btnSubmit.setForeground(Color.BLACK); // Warna teks tombol

        // Tombol Batal
        JButton btnBatal = new JButton("Batal");
        btnBatal.setBackground(Color.RED); // Warna tombol Batal
        btnBatal.setForeground(Color.WHITE); // Warna teks tombol Batal

        // Label untuk tanggal
        JLabel labelTanggal = new JLabel("Tanggal: ");
        JTextField fieldTanggal = new JTextField();

        // Tambahkan Komponen ke Panel
        panelInput.add(labelNama);
        panelInput.add(fieldNama);
        panelInput.add(labelRekening);
        panelInput.add(fieldRekening);
        panelInput.add(labelSaldo);
        panelInput.add(fieldSaldo);
        panelInput.add(labelTanggal);
        panelInput.add(fieldTanggal);
        panelInput.add(new JLabel()); // Kosong untuk padding
        panelInput.add(btnSubmit);
        panelInput.add(btnBatal); // Menambahkan tombol Batal

        // Tabel untuk Menampilkan Data Inputan
        String[] columnNames = {"Nama Nasabah", "No Rekening", "Saldo", "Tanggal"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Event Handler untuk Tombol Submit
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = fieldNama.getText().trim();
                String rekening = fieldRekening.getText().trim();
                String saldo = fieldSaldo.getText().trim();
                String tanggal = fieldTanggal.getText().trim();

                // Validasi agar input tidak kosong
                if (nama.isEmpty() || rekening.isEmpty() || saldo.isEmpty() || tanggal.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "DIISI DULU BREEE!", "Warning!!!", JOptionPane.OK_CANCEL_OPTION);
                } else {
                    // Menambahkan data ke tabel
                    tableModel.addRow(new Object[]{nama, rekening, saldo, tanggal});

                    // Membersihkan field input setelah data disimpan
                    fieldNama.setText("");
                    fieldRekening.setText("");
                    fieldSaldo.setText("");
                    fieldTanggal.setText("");
                }
            }
        });

        // Event Handler untuk Tombol Batal
        btnBatal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menghapus semua data yang ada pada field input
                fieldNama.setText("");
                fieldRekening.setText("");
                fieldSaldo.setText("");
                fieldTanggal.setText("");
            }
        });

        // Event Handler untuk Tombol Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = fieldUserId.getText().trim();
                String password = new String(fieldPassword.getPassword()).trim();

                // Cek apakah ID pengguna dan password valid
                if (userDatabase.containsKey(userId) && userDatabase.get(userId).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Login Berhasil", "Info", JOptionPane.INFORMATION_MESSAGE);

                    // Menampilkan Panel Utama setelah login
                    frame.getContentPane().removeAll();
                    frame.add(panelInput, BorderLayout.NORTH);
                    frame.add(tableScrollPane, BorderLayout.CENTER);
                    frame.revalidate();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "ID Pengguna atau Password Salah", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Event Handler untuk Tombol Sign Up
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = fieldUserId.getText().trim();
                String password = new String(fieldPassword.getPassword()).trim();

                // Cek apakah ID pengguna sudah ada
                if (userDatabase.containsKey(userId)) {
                    JOptionPane.showMessageDialog(frame, "ID Pengguna sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Menambahkan user baru ke database
                    userDatabase.put(userId, password);
                    JOptionPane.showMessageDialog(frame, "Registrasi Berhasil. Silakan Login.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Panel untuk Menampilkan Login
        JPanel loginContainer = new JPanel();
        loginContainer.setLayout(new BorderLayout());
        loginContainer.add(loginPanel, BorderLayout.CENTER);
        loginContainer.add(buttonPanel, BorderLayout.SOUTH); // Menambahkan button panel ke bagian bawah

        // Menambahkan Panel Login ke Frame Utama
        frame.add(loginContainer, BorderLayout.CENTER);

        // Menampilkan Frame
        frame.setVisible(true);
    }
}
