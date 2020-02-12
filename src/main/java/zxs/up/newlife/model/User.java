package zxs.up.newlife.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.id
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.name
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.account_id
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.token
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.gmt_create
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.gmt_modified
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.bio
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private String bio;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column my_user.avatar_url
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.id
     *
     * @return the value of my_user.id
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.id
     *
     * @param id the value for my_user.id
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.name
     *
     * @return the value of my_user.name
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.name
     *
     * @param name the value for my_user.name
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.account_id
     *
     * @return the value of my_user.account_id
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.account_id
     *
     * @param accountId the value for my_user.account_id
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.token
     *
     * @return the value of my_user.token
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.token
     *
     * @param token the value for my_user.token
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.gmt_create
     *
     * @return the value of my_user.gmt_create
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.gmt_create
     *
     * @param gmtCreate the value for my_user.gmt_create
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.gmt_modified
     *
     * @return the value of my_user.gmt_modified
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.gmt_modified
     *
     * @param gmtModified the value for my_user.gmt_modified
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.bio
     *
     * @return the value of my_user.bio
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public String getBio() {
        return bio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.bio
     *
     * @param bio the value for my_user.bio
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column my_user.avatar_url
     *
     * @return the value of my_user.avatar_url
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column my_user.avatar_url
     *
     * @param avatarUrl the value for my_user.avatar_url
     *
     * @mbg.generated Wed Feb 12 22:08:53 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}