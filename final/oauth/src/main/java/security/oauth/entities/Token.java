package security.oauth.entities;

public class Token {
    private String tokenid;
    private Long userid;
    private String tokenName;

    public Token(String tokenid, Long userid, String tokenName) {
        this.tokenid = tokenid;
        this.userid = userid;
        this.tokenName = tokenName;
    }
    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
