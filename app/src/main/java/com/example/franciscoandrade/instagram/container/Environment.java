package com.example.franciscoandrade.instagram.container;

/**
 * Created by murodjon.rahimov on 1/19/18.
 */
public class Environment {

  private final String rootUrl;
  private final String accessToken;
  private final String key;
  private final String informationUser;
  private final String accessTokenName;

  public Environment(String rootUrl, String accessToken, String key, String informationUser, String accessTokenName) {
    this.rootUrl = rootUrl;
    this.accessToken = accessToken;
    this.key = key;
    this.informationUser = informationUser;
    this.accessTokenName = accessTokenName;
  }

  public String getRootUrl() {
    return rootUrl;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getKey() {
    return key;
  }

  public String getInformationUser() {
    return informationUser;
  }

  public String getAccessTokenName() {
    return accessTokenName;
  }
}

