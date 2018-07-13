package esec.recycler.com.recyclercustomer.model;


public class InboxModel {

    String title;
    String description;
    String value;
    String statement_date;


    public InboxModel()
    {

    }

    public InboxModel(String statement_date, String title, String description, String value)
    {

        this.statement_date = statement_date;
        this.title = title;
        this.description = description;
        this.value = value;

    }



    public String getStatement_date() {
        return statement_date;
    }

    public void setStatement_date(String statement_date) {
        this.statement_date = statement_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
