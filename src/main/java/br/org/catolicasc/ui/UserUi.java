package br.org.catolicasc.ui;

import br.org.catolicasc.dao.UserDao;
import br.org.catolicasc.model.Role;
import br.org.catolicasc.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserUi {
    private JTable table1;
    private JButton novoButton;
    private JButton editarButton;
    private JButton apagarButton;
    private JButton voltarButton;
    private DefaultTableModel usersTableModel;
    private JPanel userMain;
    private JFrame usersFrame;

    public UserUi() {
        JFrame usersFrame = new JFrame("Busca de Usuários");
        usersFrame.setContentPane(userMain);
        usersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        usersFrame.pack();
        usersFrame.setLocationRelativeTo(null);
        usersFrame.setVisible(true);

        UserUi that = this;


        String[] colunas = {"Id","Login","Endereço","Cidade","Estado","Nível"};

        usersTableModel = new DefaultTableModel(new Object[][]{}, colunas);

        table1.setModel(usersTableModel);

        findUsers();

        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewUserUi(that);
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)table1.getValueAt(table1.getSelectedRow(), 0);
                if(id != 0){
                    NewUserUi u = new NewUserUi(that);
                    u.setUserToEdit(UserDao.getNewInstance().getById(id));
                }
            }
        });
        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int)table1.getValueAt(table1.getSelectedRow(), 0);
                if(id!= 0){
                    int op = JOptionPane.showConfirmDialog(null,"Tem certeza?","Apagar usuário",JOptionPane.OK_CANCEL_OPTION);
                    if(op == 0){
                        UserDao.getNewInstance().deleteById(id);
                        findUsers();
                    }
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersFrame.dispose();
            }
        });
    }

    public void findUsers(){
        List<User> listU = UserDao.getNewInstance().getAll();

        usersTableModel.setRowCount(0); // limpa a tabela

        for (User p: listU) {
            // seguindo a ordem das colunas
            usersTableModel.addRow(
                    new Object[]{
                            p.getId(),
                            p.getName(),
                            p.getAddress(),
                            p.getCity(),
                            p.getState(),
                            p.getRole()
                    }
            );
        }
    }

    public static void main(String[] args) {
        new UserUi();
    }

}
