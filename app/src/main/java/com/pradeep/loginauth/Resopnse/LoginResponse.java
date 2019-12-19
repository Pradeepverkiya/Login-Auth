package com.pradeep.loginauth.Resopnse;

public class LoginResponse
{
    private String is_admin;

    private String is_social;

    private String success;

    private String session;

    private String _country_configured;

    public String getIs_admin ()
    {
        return is_admin;
    }

    public void setIs_admin (String is_admin)
    {
        this.is_admin = is_admin;
    }

    public String getIs_social ()
    {
        return is_social;
    }

    public void setIs_social (String is_social)
    {
        this.is_social = is_social;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }


    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String get_country_configured ()
    {
        return _country_configured;
    }

    public void set_country_configured (String _country_configured)
    {
        this._country_configured = _country_configured;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [is_admin = "+is_admin+", is_social = "+is_social+", success = "+success+", session = "+session+", _country_configured = "+_country_configured+"]";
    }
}