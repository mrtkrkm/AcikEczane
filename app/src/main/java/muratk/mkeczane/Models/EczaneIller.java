package muratk.mkeczane.Models;

/**
 * Created by Murat on 08/10/15.
 */
public class EczaneIller {
    private int Id;
    private String Name;
    private String Selected;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSelected() {
        return Selected;
    }

    public void setSelected(String selected) {
        Selected = selected;
    }

    public EczaneIller() {
    }

    public EczaneIller(int id, String name, String selected) {
        Id = id;
        Name = name;
        Selected = selected;
    }
}
