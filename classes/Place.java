package classes;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Place
{
  @Id
  @GeneratedValue(strategy=GenerationType.TABLE)
  @Column(name = "PLACE_ID", unique = true, nullable = false)
  private int id_;

  private String name_;
  private int roomcp_;
  private int roomnr_;

// DEFAULT CONSTRUCTOR
  @SuppressWarnings("unused")
  public Place(){}

  public Place(String name, int roomcp, int roomnr)
  {
    this.name_ = name;
    this.roomcp_ = roomcp;
    this.roomnr_ = roomnr;
  }

  @Override
  public String toString() {
    return name_;
  }

// GETTER ID_
//------------------------------------------------------------------------------
    public final int getID()
    {
      return id_;
    }
//------------------------------------------------------------------------------

// GETTER SETTER NAME_
//------------------------------------------------------------------------------
    public final String getName()
    {
      return name_;
    }
    public final void setName(String name)
    {
      this.name_ = name;
    }
//------------------------------------------------------------------------------

// GETTER SETTER ROOMNR
//------------------------------------------------------------------------------
    public final int getRoomNr()
    {
      return roomnr_;
    }
    public final void setRoomNr(int roomnr)
    {
      this.roomnr_ = roomnr;
    }
//------------------------------------------------------------------------------

// GETTER SETTER ROOMCP
//------------------------------------------------------------------------------
    public final int getCapacity()
    {
      return roomcp_;
    }
    public final void setRoomCp(int roomcp)
    {
      this.roomcp_ = roomcp;
    }
//------------------------------------------------------------------------------
}
