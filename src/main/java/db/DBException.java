package db;
public class DBException extends Exception
{
    public DBException(String msg)
    {
        super(msg);
    }
    public DBException(Exception ex)
    {
        super(ex);
    }
}