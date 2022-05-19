
  export interface Place {
      rateLimitStatus?: any;
      accessLevel: number;
      name: string;
      streetAddress?: any;
      countryCode: string;
      id: string;
      country: string;
      placeType: string;
      url: string;
      fullName: string;
      boundingBoxType: string;
      boundingBoxCoordinates: any[][];
      geometryType?: any;
      geometryCoordinates?: any;
      containedWithIn?: any;
  }

  export interface Urlentity {
      start: number;
      end: number;
      url: string;
      expandedURL: string;
      displayURL: string;
      text: string;
  }

  export interface User {
      rateLimitStatus?: any;
      accessLevel: number;
      id: number;
      name: string;
      email?: any;
      screenName: string;
      location?: any;
      description: string;
      descriptionURLEntities: any[];
      url?: any;
      followersCount: number;
      status?: any;
      profileBackgroundColor: string;
      profileTextColor: string;
      profileLinkColor: string;
      profileSidebarFillColor: string;
      profileSidebarBorderColor: string;
      profileUseBackgroundImage: boolean;
      showAllInlineMedia: boolean;
      friendsCount: number;
      createdAt: number;
      favouritesCount: number;
      utcOffset: number;
      timeZone?: any;
      profileBackgroundImageUrlHttps: string;
      profileBackgroundTiled: boolean;
      lang?: any;
      statusesCount: number;
      translator: boolean;
      listedCount: number;
      withheldInCountries: any[];
      protected: boolean;
      profileImageURL: string;
      defaultProfile: boolean;
      verified: boolean;
      urlentity: Urlentity;
      geoEnabled: boolean;
      profileImageURLHttps: string;
      profileBannerMobileURL: string;
      originalProfileImageURLHttps: string;
      profileBannerIPadRetinaURL: string;
      followRequestSent: boolean;
      miniProfileImageURLHttps: string;
      profileBannerURL: string;
      profileBanner1500x500URL: string;
      contributorsEnabled: boolean;
      defaultProfileImage: boolean;
      profileBackgroundImageURL: string;
      profileBannerMobileRetinaURL: string;
      profileBannerRetinaURL: string;
      // 400x400ProfileImageURLHttps: string;
      biggerProfileImageURLHttps: string;
      profileBanner600x200URL: string;
      biggerProfileImageURL: string;
      miniProfileImageURL: string;
      profileBannerIPadURL: string;
      originalProfileImageURL: string;
      // 400x400ProfileImageURL: string;
      profileBanner300x100URL: string;
  }

  export interface Urlentity2 {
      start: number;
      end: number;
      url: string;
      expandedURL: string;
      displayURL: string;
      text: string;
  }

  export interface User2 {
      rateLimitStatus?: any;
      accessLevel: number;
      id: number;
      name: string;
      email?: any;
      screenName: string;
      location?: any;
      description: string;
      descriptionURLEntities: any[];
      url: string;
      followersCount: number;
      status?: any;
      profileBackgroundColor: string;
      profileTextColor: string;
      profileLinkColor: string;
      profileSidebarFillColor: string;
      profileSidebarBorderColor: string;
      profileUseBackgroundImage: boolean;
      showAllInlineMedia: boolean;
      friendsCount: number;
      createdAt: number;
      favouritesCount: number;
      utcOffset: number;
      timeZone?: any;
      profileBackgroundImageUrlHttps: string;
      profileBackgroundTiled: boolean;
      lang?: any;
      statusesCount: number;
      translator: boolean;
      listedCount: number;
      withheldInCountries: any[];
      protected: boolean;
      profileImageURL: string;
      defaultProfile: boolean;
      verified: boolean;
      urlentity: Urlentity2;
      geoEnabled: boolean;
      profileImageURLHttps: string;
      profileBannerMobileURL: string;
      originalProfileImageURLHttps: string;
      profileBannerIPadRetinaURL: string;
      followRequestSent: boolean;
      miniProfileImageURLHttps: string;
      profileBannerURL: string;
      profileBanner1500x500URL: string;
      contributorsEnabled: boolean;
      defaultProfileImage: boolean;
      profileBackgroundImageURL: string;
      profileBannerMobileRetinaURL: string;
      profileBannerRetinaURL: string;
      // 400x400ProfileImageURLHttps: string;
      biggerProfileImageURLHttps: string;
      profileBanner600x200URL: string;
      biggerProfileImageURL: string;
      miniProfileImageURL: string;
      profileBannerIPadURL: string;
      originalProfileImageURL: string;
      // 400x400ProfileImageURL: string;
      profileBanner300x100URL: string;
  }

  export interface Urlentity3 {
      start: number;
      end: number;
      url: string;
      expandedURL: string;
      displayURL: string;
      text: string;
  }

  export interface QuotedStatus {
      rateLimitStatus?: any;
      accessLevel: number;
      createdAt: number;
      id: number;
      text: string;
      displayTextRangeStart: number;
      displayTextRangeEnd: number;
      source: string;
      inReplyToStatusId: number;
      inReplyToUserId: number;
      favoriteCount: number;
      inReplyToScreenName?: any;
      geoLocation?: any;
      place?: any;
      retweetCount: number;
      lang: string;
      retweetedStatus?: any;
      userMentionEntities: any[];
      hashtagEntities: any[];
      mediaEntities: any[];
      symbolEntities: any[];
      currentUserRetweetId: number;
      scopes?: any;
      user: User2;
      withheldInCountries?: any;
      quotedStatus?: any;
      quotedStatusId: number;
      quotedStatusPermalink?: any;
      contributors: any[];
      possiblySensitive: boolean;
      retweetedByMe: boolean;
      truncated: boolean;
      favorited: boolean;
      urlentities: Urlentity3[];
      retweet: boolean;
      retweeted: boolean;
  }

  export interface QuotedStatusPermalink {

      start: number;
      end: number;
      url: string;
      expandedURL: string;
      displayURL: string;
      text: string;
  }

  export interface Tweet {

      rateLimitStatus?: any;
      accessLevel: number;
      createdAt: number;
      id: number;
      text: string;
      displayTextRangeStart: number;
      displayTextRangeEnd: number;
      source: string;
      inReplyToStatusId: number;
      inReplyToUserId: number;
      favoriteCount: number;
      inReplyToScreenName?: any;
      geoLocation?: any;
      place: Place;
      retweetCount: number;
      lang: string;
      retweetedStatus?: any;
      userMentionEntities: any[];
      hashtagEntities: any[];
      mediaEntities: any[];
      symbolEntities: any[];
      currentUserRetweetId: number;
      scopes?: any;
      user: User;
      withheldInCountries?: any;
      quotedStatus: QuotedStatus;
      quotedStatusId: number;
      quotedStatusPermalink: QuotedStatusPermalink;
      contributors: any[];
      possiblySensitive: boolean;
      retweetedByMe: boolean;
      truncated: boolean;
      favorited: boolean;
      urlentities: any[];
      retweet: boolean;
      retweeted: boolean;
  }
