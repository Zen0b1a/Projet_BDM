/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

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
    private ARRAY telephone;
    private STRUCT photo;
    
    public Personne()
    {
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
        Object[] adresse_attributs = null;
        try
        {
            adresse_attributs = this.adresse.getAttributes();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(adresse_attributs[0].toString());
    }
    
    public String getNomRue()
    {
        Object[] adresse_attributs = null;
        try
        {
            adresse_attributs = this.adresse.getAttributes();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adresse_attributs[1].toString();
    }
    
    public String getVille()
    {
        Object[] adresse_attributs = null;
        try
        {
            adresse_attributs = this.adresse.getAttributes();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adresse_attributs[2].toString();
    }
    
    public String getTelephone1()
    {
        try
        {
            Object[] tel = (Object[])this.telephone.getArray();
            return ((STRUCT)tel[0]).getAttributes()[0].toString();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getTelephone2()
    {
        try
        {
            Object[] tel = (Object[])this.telephone.getArray();
            return ((STRUCT)tel[1]).getAttributes()[0].toString();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Personne chargerPersonne(int id)
    {
        Personne pers = null;
        try 
        {
            java.util.Map maMap = ConnexionUtils.getInstance().getTypeMap();
            System.out.println(maMap.toString());
            maMap.put("AG092850.BDM_PERSONNE_TYPE",Class.forName("mapping.Personne"));
            System.out.println(maMap.toString());
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
            this.photo = (STRUCT)stream.readObject();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Personne.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
