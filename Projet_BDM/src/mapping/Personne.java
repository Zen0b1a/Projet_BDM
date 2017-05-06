/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.*;
import oracle.ord.im.OrdImage;
import oracle.sql.*;
import utils.ConnexionUtils;
/**
 *
 * @author Annabelle
 */
public class Personne implements SQLData {
    private String sql_type;
    private int id;
    private String nom;
    private String prenom;
    private STRUCT adresse;
    private int numeroRue;
    private String rue;
    private String ville;
    private ARRAY telephone;
    private String[] telephones;
    private OrdImage photo;
    private String cheminPhoto;
    
    public Personne()
    {
    }

    public Personne(String nom, String prenom, int numeroRue, String rue, String ville, String telephone1, String telephone2, String cheminPhoto)
    {
        this.id = 0;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroRue = numeroRue;
        this.rue = rue;
        this.ville = ville;
        this.telephones = new String[2];
        this.telephones[0] = telephone1;
        this.telephones[1] = telephone2;
        this.cheminPhoto = cheminPhoto;
    }
    
    public String getSql_type()
    {
        return sql_type;
    }

    public int getId()
    {
        return id;
    }

    public String getNom()
    {
        return nom;
    }

    public String getPrenom()
    {
        return prenom;
    }
    
    public int getNumeroRue()
    {
        return this.numeroRue;
    }
    
    public String getNomRue()
    {
        return this.rue;
    }
    
    public String getVille()
    {
        return this.ville;
    }
    
    public String getTelephone1()
    {
        return this.telephones[0];
    }
    
    public String getTelephone2()
    {
        return this.telephones[1];
    }
    
    public String getCheminPhoto()
    {
        return this.cheminPhoto;
    }
    
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }
    
    public void setNumeroRue(int numeroRue)
    {
        this.numeroRue = numeroRue;
    }
    
    public void setRue(String rue)
    {
        this.rue = rue;
    }
    
    public void setVille(String ville)
    {
        this.ville = ville;
    }
    
    public void setTelephone(int indice, String telephone)
    {
        this.telephones[indice-1] = telephone;
    }
    
    public Personne chargerPersonne(int id)
    {
        Personne pers = null;
        try 
        {
            java.util.Map maMap = ConnexionUtils.getInstance().getTypeMap();
            maMap.put("AG092850.BDM_PERSONNE_TYPE",Class.forName("mapping.Personne"));
            OracleStatement stmt = (OracleStatement) ConnexionUtils.getInstance().createStatement();
            OracleResultSet rs = (OracleResultSet) stmt.executeQuery("SELECT VALUE(p) FROM bdm_personne p WHERE id="+id);
            rs.next();
            pers = (Personne)rs.getObject(1, maMap);
            rs.close();
            stmt.close();
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pers;
    }
    
    public boolean majTelephone(int indice, String nouveauTelephone)
    {
        if(indice>0 && indice<3)
            this.setTelephone(indice, nouveauTelephone);
        else
            return false;
        try 
        {
            CallableStatement stmt = ConnexionUtils.getInstance().prepareCall("{call majPersonneTelephone(?, ?, ?)}");
            stmt.setInt(1, this.id);
            stmt.setInt(2, indice);
            stmt.setString(3, nouveauTelephone);
            stmt.execute();
            stmt.close();
            return true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean majAdresse(int nouveauNumeroRue, String nouvelleRue, String nouvelleVille)
    {
        this.setNumeroRue(nouveauNumeroRue);
        this.setRue(nouvelleRue);
        this.setVille(nouvelleVille);
        try 
        {
            CallableStatement stmt = ConnexionUtils.getInstance().prepareCall("{call majPersonneAdresse(?, ?, ?, ?)}");
            stmt.setInt(1, this.id);
            stmt.setInt(2, this.getNumeroRue());
            stmt.setString(3, this.getNomRue());
            stmt.setString(4, this.getVille());
            stmt.execute();
            stmt.close();
            return true;
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean enregistrerPersonne()
    {
        try
        {
            //Récupération de l'id
            OraclePreparedStatement stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("SELECT MAX(id) FROM bdm_personne");
            OracleResultSet rs = (OracleResultSet)stmt.executeQuery();
            rs.next();
            this.id = rs.getInt(1)+1;
            ConnexionUtils.getInstance().setAutoCommit(false);
            //Insertion
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("INSERT INTO bdm_personne VALUES(?, ?, ?, "
                + "bdm_adresse_type(?, ?, ?), bdm_telephones_type(bdm_telephone_type(?), bdm_telephone_type(?)), ORDSYS.ORDImage.init())");
            stmt.setInt(1, this.id);
            stmt.setString(2, this.nom);
            stmt.setString(3, prenom);
            stmt.setInt(4, this.numeroRue);
            stmt.setString(5, this.rue);
            stmt.setString(6, this.ville);
            stmt.setString(7, this.telephones[0]);
            stmt.setString(8, this.telephones[1]);
            stmt.executeQuery();
            //Ajout de la photo
            Statement ps = (Statement)ConnexionUtils.getInstance().createStatement();
            rs = (OracleResultSet)ps.executeQuery("SELECT photo FROM bdm_personne WHERE id="+this.id+" FOR UPDATE");
            rs.next();
            this.photo = (OrdImage)rs.getORAData(1, OrdImage.getORADataFactory());
            this.photo.loadDataFromFile(this.cheminPhoto);
            this.photo.setProperties();
            stmt = (OraclePreparedStatement)ConnexionUtils.getInstance().prepareStatement("UPDATE bdm_personne SET photo=? WHERE id=?");
            stmt.setORAData(1, this.photo);
            stmt.setInt(2, this.id);
            stmt.execute();
            ConnexionUtils.getInstance().commit();
            ps.close();
            rs.close();
            stmt.close();
            ConnexionUtils.getInstance().setAutoCommit(true);
            return true;
        }
        catch (SQLException | IOException ex)
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    @Override
    public String getSQLTypeName() 
    {
        return "BDM_PERSONNE_TYPE";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) 
    {
        try 
        {
            this.sql_type = typeName;
            this.id = stream.readInt();
            this.nom = stream.readString();
            this.prenom = stream.readString();
            this.adresse = (STRUCT)stream.readObject();
            this.telephone = (ARRAY)stream.readArray();
            this.photo = (OrdImage)((STRUCT)stream.readObject()).toClass(OrdImage.class);
            this.cheminPhoto = "temp/personne/"+this.id;
            this.photo.getDataInFile(this.cheminPhoto);
            this.numeroRue = Integer.parseInt(this.adresse.getAttributes()[0].toString());
            this.rue = this.adresse.getAttributes()[1].toString();
            this.ville = this.adresse.getAttributes()[2].toString();
            this.telephones = new String[2];
            Object[] tel = (Object[])this.telephone.getArray();
            this.telephones[0] = ((STRUCT)tel[0]).getAttributes()[0].toString();
            this.telephones[1] = ((STRUCT)tel[1]).getAttributes()[0].toString();
        } 
        catch (SQLException | IOException ex)
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
