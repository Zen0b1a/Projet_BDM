/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Annabelle
 */
public class Adresse implements SQLData
{
    private String sql_type;
    private int numeroRue;
    private String rue;
    private String ville;

    public Adresse()
    {
        
    }
    
    public Adresse(int numeroRue, String rue, String ville)
    {
        this.numeroRue = numeroRue;
        this.rue = rue;
        this.ville = ville;
    }
    
    public int getNumeroRue()
    {
        return this.numeroRue;
    }

    public void setNumeroRue(int numeroRue)
    {
        this.numeroRue = numeroRue;
    }

    public String getRue()
    {
        return this.rue;
    }

    public void setRue(String rue)
    {
        this.rue = rue;
    }

    public String getVille()
    {
        return this.ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }
    
    @Override
    public String getSQLTypeName() 
    {
        return "BDM_ADRESSE_TYPE";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) 
    {
        try 
        {
            this.sql_type = typeName;
            this.numeroRue = stream.readInt();
            this.rue = stream.readString();
            this.ville = stream.readString();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Adresse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(this.numeroRue);
        stream.writeString(this.rue);
        stream.writeString(this.ville);
    }
    
}
