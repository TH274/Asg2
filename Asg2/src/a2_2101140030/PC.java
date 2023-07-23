package a2_2101140030;


import java.util.Objects;
import utils.AttrRef;
import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.NotPossibleException;

/**
 * @overview A PC class that return all the information of the computer
 * @attributes
 *             model String
 *             year int
 *             manufacturer String
 *             comps Set<String>
 * @object A PC consist of c=<m,y,mr,c>
 *         where model(m), year(y), manufacturer(mr), comps(c).
 * @pseudocode
 * @abstract_properties
 *                      Mutable(model)=true /\ Optional(model)=false /\ length(model)=30
 *                      Mutable(year)=true /\ Optional(year)=false /\ min(year) = 1970
 *                      Mutable(manufacturer)= false /\ Optional(manufacturer)=false /\ length(manufacturer) = 30
 *                      Mutable(comps)=true /\ Optional(comps)=false
 * @requires model, year, manufacturer, comps are not null
 * @modifies
 * @effects Return the PC information
 *
 */
public class PC {


    @DomainConstraint(type = "String",mutable = true,optional = false,length = 30)
    private String model;
    @DomainConstraint(type = "Integer",mutable = false,optional = false,min = 1970)
    private int year;
    @DomainConstraint(type = "String",mutable = false,optional = false,length = 20)
    private String manufacturer;
    @DomainConstraint(type = "String",mutable = true,optional = false)
    private Set<String> comps;

    @DOpt(type = OptType.Constructor)
    public PC(@AttrRef("model") String model,@AttrRef("year") int year,@AttrRef("manufacturer") String manufacturer,@AttrRef("comps") Set<String> comps) {
        this.model = model;
        this.year = year;
        this.manufacturer = manufacturer;
        this.comps = comps;
    }

    /**
     * @effects
     * return model
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("model")
    public String getModel() {
        if (!modelValidate(model)) {
            throw new NotPossibleException("Wrong PC model" + model);
        }
        return this.model;
    }

    @DOpt(type = OptType.Helper)
    private boolean modelValidate(String model) {
        if (model == null) {
            return false;
        } if (model.length() > 30){
            return false;
        }
        return true;
    }

    /**
     * @effects
     * return year
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("year")
    public int getYear() {
        if (!yearValidate(year)) {
            throw new NotPossibleException("Wrong year" + year);
        }
        return this.year;
    }
    @DOpt(type = OptType.Helper)
    private boolean yearValidate (int year) {
        if (year < 1970) {
            return false;
        }
        return true;
    }

    /**
     * @effects
     * return manufacturer
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("manufacturer")
    public String getManufacturer() {
        if (!manufacturerValidate(manufacturer)) {
            throw new NotPossibleException("Wrong manufacturer" + manufacturer);
        }
        return this.manufacturer;
    }
    @DOpt(type = OptType.Helper)
    private boolean manufacturerValidate(String manufacturer) {
        if (manufacturer == null) {
            return false;
        } if (manufacturer.length() > 20) {
            return false;
        }
        return true;
    }

    /**
     * @effects
     * return components
     */
    @DOpt(type = OptType.Observer)
    @AttrRef("comps")
    public Set<String> getComps() {
        if (!compsValidate(comps)) {
            throw new NotPossibleException("Wrong PC components" + comps);
        }
        return this.comps;
    }
    @DOpt(type = OptType.Helper)
    private boolean compsValidate (Set<String> comps){
        if (comps == null) {
            return false;
        }
        return true;
    }

    @DOpt(type = OptType.Default)
    @Override
    public String toString() {
            return "PC<" + model + "," + year + "," + manufacturer + "," + comps + ">";
    }

    @DOpt(type = OptType.Helper)
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PC))
            return false;
        PC other = (PC) obj;
        return Objects.equals(model, other.model) && year == other.year
                && Objects.equals(manufacturer, other.manufacturer) && Objects.equals(comps, other.comps);
    }
}