package com.cometproject.server.network.multirevision.revisions;

import com.cometproject.server.network.multirevision.HeaderTranslator;
import com.cometproject.server.network.multirevision.registrars.PacketRegistrar;
import com.cometproject.server.protocol.headers.Events;

public class AIR63_201911271159_623255659 implements PacketRegistrar {
    private final String revision = "AIR63-201911271159-623255659";

    @Override
    public void registerIncoming(HeaderTranslator translator) {
        //handshake
        translator.registerIncomingPacketForRevision(revision, Events.GetClientVersionMessageEvent, 4000);//ClientHello
        translator.registerIncomingPacketForRevision(revision, Events.InitDiffieHandshake,2298);//InitDiffieHandshake
        translator.registerIncomingPacketForRevision(revision, Events.GenerateSecretKeyMessageEvent,1259);//CompleteDiffieHandshake
        translator.registerIncomingPacketForRevision(revision, 1053, 2652); //VersionCheck
        translator.registerIncomingPacketForRevision(revision, Events.UniqueIDMessageEvent, 2706);//UniqueID
        translator.registerIncomingPacketForRevision(revision, Events.SSOTicketMessageEvent, 795);//SSOTicket
        translator.registerIncomingPacketForRevision(revision, Events.InfoRetrieveMessageEvent,2157);//InfoRetrieve
        translator.registerIncomingPacketForRevision(revision, Events.PongEvent, 3946);//Pong

        //advertisement
        translator.registerIncomingPacketForRevision(revision, 2519, 3312);//GetInterstitial
        translator.registerIncomingPacketForRevision(revision, 1109, 112);//InterstitialShown

        //avatar
        translator.registerIncomingPacketForRevision(revision, 2977, 1004);//ChangeUserName
        translator.registerIncomingPacketForRevision(revision, 3950, 2444);//CheckUserName
        translator.registerIncomingPacketForRevision(revision, 2742, 3976);//GetWardrobe
        translator.registerIncomingPacketForRevision(revision, 800, 500);//SaveWardrobeOutfit

        //camera
        translator.registerIncomingPacketForRevision(revision, 3959, 2939);//PhotoCompetition
        translator.registerIncomingPacketForRevision(revision, 2068, 1562);//PublishPhoto
        translator.registerIncomingPacketForRevision(revision, 2408, 212);//PurchasePhoto
        translator.registerIncomingPacketForRevision(revision, 3226, 1594);//RenderRoom ??
        translator.registerIncomingPacketForRevision(revision, 1982, 3866);//RenderRoom_PARENT ??
        translator.registerIncomingPacketForRevision(revision, 796, 656);//RequestCameraConfiguration

        //campaign
        translator.registerIncomingPacketForRevision(revision, 2257, 3702);//OpenCampaignCalendarDoor
        translator.registerIncomingPacketForRevision(revision, 3589, 2375);//OpenCampaignCalendarDoorAsStaff

        //catalog
        translator.registerIncomingPacketForRevision(revision, 223, 3126);//GetBundleDiscountRuleset
        translator.registerIncomingPacketForRevision(revision, 1195, 3881);//GetCatalogIndex
        translator.registerIncomingPacketForRevision(revision, 412, 261);//GetCatalogPage
        translator.registerIncomingPacketForRevision(revision, 3492, 265);//PurchaseFromCatalog
        translator.registerIncomingPacketForRevision(revision, 1411, 1017);//PurchaseFromCatalogAsGift
        translator.registerIncomingPacketForRevision(revision, 2529, 100);//BuildersClubQueryFurniCount
        translator.registerIncomingPacketForRevision(revision, 1051, 3387);//BuildersClubPlaceRoomItem
        translator.registerIncomingPacketForRevision(revision, 462, 683);//BuildersClubPlaceWallItem
        translator.registerIncomingPacketForRevision(revision, 742, 3506);//GetCatalogPageExpiration
        translator.registerIncomingPacketForRevision(revision, 3285, 3664);//GetClubOffers
        translator.registerIncomingPacketForRevision(revision, 801, 197);//GetDirectClubBuyAvailable
        translator.registerIncomingPacketForRevision(revision, 603, 3054);//GetHabboBasicMembershipExtendOffer
        translator.registerIncomingPacketForRevision(revision, 2462,3644);//GetHabboClubExtendOffer
        translator.registerIncomingPacketForRevision(revision, 1347, 1297);//GetIsOfferGiftable
        translator.registerIncomingPacketForRevision(revision, 596, 2535);//GetNextTargetedOffer
        translator.registerIncomingPacketForRevision(revision, 2594, 620);//GetProductOffer
        translator.registerIncomingPacketForRevision(revision, 1756, 3306);//GetSellablePetPalettes
        translator.registerIncomingPacketForRevision(revision, 2487, 493);//GetTargetedOffer
        translator.registerIncomingPacketForRevision(revision, 2735, 529);//PurchaseBasicMembershipExtension
        translator.registerIncomingPacketForRevision(revision, 777, 823);//PurchaseRoomAd
        translator.registerIncomingPacketForRevision(revision, 1826, 2741);//PurchaseTargetedOffer
        translator.registerIncomingPacketForRevision(revision, 3407, 2568);//PurchaseVipMembershipExtension
        translator.registerIncomingPacketForRevision(revision, 339, 2332);//RedeemVoucher
        translator.registerIncomingPacketForRevision(revision, 2276, 3466);//SelectClubGift
        translator.registerIncomingPacketForRevision(revision, 2041, 3975);//SetTargetedOfferState
        translator.registerIncomingPacketForRevision(revision, 3483, 774);//ShopTargetedOfferViewed
        translator.registerIncomingPacketForRevision(revision, 418, 2597);//GetGiftWrappingConfiguration
        /*       [ 134] Catalog - Unknown_134
                [1652] Catalog - Unknown_1652
                [2181] Catalog - Unknown_2181
                [2362] Catalog - Unknown_2362
                [2692] Catalog - Unknown_2692
                [ 277] Catalog - Unknown_277
                [3209] Catalog - Unknown_3209
                [3892] Catalog - Unknown_3892
                [ 945] Catalog - Unknown_945 */

        //competition
        translator.registerIncomingPacketForRevision(revision, 2912, 2099);//GetCurrentTimingCode
        translator.registerIncomingPacketForRevision(revision, 1334, 580); //RoomCompetitionInit
        translator.registerIncomingPacketForRevision(revision, 172, 2123);//ForwardToACompetitionRoom
        translator.registerIncomingPacketForRevision(revision, 865, 430);//ForwardToRandomCompetitionRoom
        translator.registerIncomingPacketForRevision(revision, 2077, 3645);//GetIsUserPartOfCompetition
        translator.registerIncomingPacketForRevision(revision, 271, 2076);//GetSecondsUntil
        translator.registerIncomingPacketForRevision(revision, 2595, 438);//SubmitRoomToCompetition
        translator.registerIncomingPacketForRevision(revision, 143, 224);//VoteForRoom
        translator.registerIncomingPacketForRevision(revision, 1450, 2361);//ForwardToASubmittableRoomMessageComposer

        //crafting
        translator.registerIncomingPacketForRevision(revision, 3591, 1659);//Craft
        translator.registerIncomingPacketForRevision(revision, 1251, 3815);//CraftSecret
        translator.registerIncomingPacketForRevision(revision, 633, 1436);//GetCraftableProducts
        translator.registerIncomingPacketForRevision(revision, 1173, 140);//GetCraftingRecipe
        translator.registerIncomingPacketForRevision(revision, 3086, 1023);//GetCraftingRecipesAvailable

        //feedback
        //translator.registerIncomingPacketForRevision(revision, 0, 3843);//UserFeedback

        //friendfurni
        translator.registerIncomingPacketForRevision(revision, 3775, 2552);//FriendFurniConfirmLock

        //friendlist
        translator.registerIncomingPacketForRevision(revision, 2781, 2044);//MessengerInit
        translator.registerIncomingPacketForRevision(revision, 2448, 3986);//GetFriendRequests
        translator.registerIncomingPacketForRevision(revision, 1210, 3093);//HabboSearch
        translator.registerIncomingPacketForRevision(revision, 3157, 222);//RequestFriend
        translator.registerIncomingPacketForRevision(revision, 2890, 3594);//DeclineFriend
        translator.registerIncomingPacketForRevision(revision, 137, 2604);//AcceptFriend
        translator.registerIncomingPacketForRevision(revision, 3567, 1873);//SendMsg
        translator.registerIncomingPacketForRevision(revision, 2970, 2577);//FollowFriend
        translator.registerIncomingPacketForRevision(revision, 1689, 401);//RemoveFriend
        translator.registerIncomingPacketForRevision(revision, 1276, 906);//SendRoomInvite
        translator.registerIncomingPacketForRevision(revision, 3768, 3404);//SetRelationshipStatus
        translator.registerIncomingPacketForRevision(revision, 3997, 1139);//VisitUser
        //translator.registerIncomingPacketForRevision(revision, 0, 1189);//Unknown_1189
        //translator.registerIncomingPacketForRevision(revision, 0, 769);//Unknown_769
        // FindNewFriendsMessageComposer or FriendListUpdateMessageComposer

        //Game.Arena
        translator.registerIncomingPacketForRevision(revision, 1445, 443);//Game2ExitGame
        translator.registerIncomingPacketForRevision(revision, 2502, 3853);//Game2GameChat
        translator.registerIncomingPacketForRevision(revision, 2415, 866);//Game2LoadStageReady
        translator.registerIncomingPacketForRevision(revision, 3196, 644);//Game2PlayAgain

        //Game.Directory
        translator.registerIncomingPacketForRevision(revision, 3259, 388);//Game2CheckGameDirectoryStatus
        translator.registerIncomingPacketForRevision(revision, 11, 181);//Game2GetAccountGameStatus

        //Game.Ingame
        translator.registerIncomingPacketForRevision(revision, 1598, 2621);//Game2RequestFullStatusUpdate

        //Game.Lobby
        translator.registerIncomingPacketForRevision(revision, 3802, 2725);//AcceptGameInvite
        translator.registerIncomingPacketForRevision(revision, 3207, 2777);//GameUnloaded
        translator.registerIncomingPacketForRevision(revision, 3171, 27);//GetGameStatus
        translator.registerIncomingPacketForRevision(revision, 359, 992);//GetResolutionAchievements
        translator.registerIncomingPacketForRevision(revision, 389, 3403);//GetUserGameAchievements
        translator.registerIncomingPacketForRevision(revision, 1458, 2620);//JoinQueue
        translator.registerIncomingPacketForRevision(revision, 2384, 2590);//LeaveQueue
        translator.registerIncomingPacketForRevision(revision, 3144, 807);//ResetResolutionAchievement
        //translator.registerIncomingPacketForRevision(revision, 0, 1743);//Unknown_1743
        //translator.registerIncomingPacketForRevision(revision, 0, 835);//Unknown_835
        //GetGameList or GetGameAchievements

        //Game.Score
        translator.registerIncomingPacketForRevision(revision, 2914, 1971);//GetWeeklyGameReward
        translator.registerIncomingPacketForRevision(revision, 1054, 2042);//GetWeeklyGameRewardWinners
        translator.registerIncomingPacketForRevision(revision, 1232, 382);//Game2GetWeeklyFriendsLeaderboard
        translator.registerIncomingPacketForRevision(revision, 2565, 3166);//Game2GetWeeklyLeaderboard
        //translator.registerIncomingPacketForRevision(revision, 0, 1958);//GetFriendsWeeklyCompetitiveLeaderboard
        //translator.registerIncomingPacketForRevision(revision, 0, 598);//GetWeeklyCompetitiveLeaderboard
        //translator.registerIncomingPacketForRevision(revision, 0, 2972);//Game2GetFriendsLeaderboard
        //translator.registerIncomingPacketForRevision(revision, 0, 2768);//Game2GetTotalLeaderboard

        //Gifts
        translator.registerIncomingPacketForRevision(revision, 2436, 2038);//GetGift
        translator.registerIncomingPacketForRevision(revision, 2741, 933);//ResetPhoneNumberState
        translator.registerIncomingPacketForRevision(revision, 1379, 1804);//SetPhoneNumberVerificationStatus
        translator.registerIncomingPacketForRevision(revision, 790, 2228);//TryPhoneNumber
        translator.registerIncomingPacketForRevision(revision, 2721, 1752);//VerifyCode

        //Groupforums
        translator.registerIncomingPacketForRevision(revision, 436, 3943);//GetForumsList
        translator.registerIncomingPacketForRevision(revision, 3149, 3650);//GetForumStats
        translator.registerIncomingPacketForRevision(revision, 232, 1586);//GetMessages
        translator.registerIncomingPacketForRevision(revision, 3900, 2899);//GetThread
        translator.registerIncomingPacketForRevision(revision, 873, 816);//GetThreads
        translator.registerIncomingPacketForRevision(revision, 2908, 2417);//GetUnreadForumsCount
        translator.registerIncomingPacketForRevision(revision, 286, 2030);//ModerateMessage
        translator.registerIncomingPacketForRevision(revision, 1397, 1445);//ModerateThread
        translator.registerIncomingPacketForRevision(revision, 3529, 3542);//PostMessage
        translator.registerIncomingPacketForRevision(revision, 1855, 1455);//UpdateForumReadMarker
        translator.registerIncomingPacketForRevision(revision, 2214, 1672);//UpdateForumSettings
        translator.registerIncomingPacketForRevision(revision, 3045, 1145);//UpdateThread

        //Help
        translator.registerIncomingPacketForRevision(revision, 1691, 794);//CallForHelp
        translator.registerIncomingPacketForRevision(revision, 1412, 2188);//CallForHelpFromForumMessage
        translator.registerIncomingPacketForRevision(revision, 534, 2446);//CallForHelpFromForumThread
        translator.registerIncomingPacketForRevision(revision, 2950, 1465);//CallForHelpFromIM
        translator.registerIncomingPacketForRevision(revision, 2492, 3375);//CallForHelpFromPhoto
        translator.registerIncomingPacketForRevision(revision, 2755, 1712);//CallForHelpFromSelfie
        translator.registerIncomingPacketForRevision(revision, 3365, 812);//ChatReviewGuideDecidesOnOffer
        translator.registerIncomingPacketForRevision(revision, 2501, 3696);//ChatReviewGuideDetached
        translator.registerIncomingPacketForRevision(revision, 3961, 577);//ChatReviewGuideVote
        translator.registerIncomingPacketForRevision(revision, 3060, 37);//ChatReviewSessionCreate
        translator.registerIncomingPacketForRevision(revision, 3445, 1630);//GetFaqCategory
        translator.registerIncomingPacketForRevision(revision, 1849, 2466);//GetFaqText
        translator.registerIncomingPacketForRevision(revision, 1296, 2205);//GetQuizQuestions
        translator.registerIncomingPacketForRevision(revision, 3338, 2288);//GuideSessionCreate
        translator.registerIncomingPacketForRevision(revision, 477, 3868);//GuideSessionFeedback
        translator.registerIncomingPacketForRevision(revision, 1052, 1969);//GuideSessionGetRequesterRoom
        translator.registerIncomingPacketForRevision(revision, 1424, 3102);//GuideSessionGuideDecides
        translator.registerIncomingPacketForRevision(revision, 234,1062);//GuideSessionInviteRequester
        translator.registerIncomingPacketForRevision(revision, 519, 666);//GuideSessionIsTyping
        translator.registerIncomingPacketForRevision(revision, 3899, 1536);//GuideSessionMessage
        translator.registerIncomingPacketForRevision(revision, 1922, 735);//GuideSessionOnDutyUpdate
        translator.registerIncomingPacketForRevision(revision, 3969, 2297);//GuideSessionReport
        translator.registerIncomingPacketForRevision(revision, 291, 484);//GuideSessionRequesterCancels
        translator.registerIncomingPacketForRevision(revision, 887, 1339);//GuideSessionResolved
        translator.registerIncomingPacketForRevision(revision, 3720, 1338);//PostQuizAnswers
        translator.registerIncomingPacketForRevision(revision, 2031, 2034);//SearchFaqs
        //translator.registerIncomingPacketForRevision(revision, 0,1218);//Unknown_1218
        //translator.registerIncomingPacketForRevision(revision, 0, 2348);//Unknown_2348
        //translator.registerIncomingPacketForRevision(revision, 0, 2962);//Unknown_2962
        //translator.registerIncomingPacketForRevision(revision, 0, 2965);//Unknown_2965
        //translator.registerIncomingPacketForRevision(revision, 0, 3141);//Unknown_3141

        //inventory.avatareffect
        translator.registerIncomingPacketForRevision(revision, 2959, 2945);//AvatarEffectActivated
        translator.registerIncomingPacketForRevision(revision, 3473, 3358);//AvatarEffectSelected

        //inventory.badges
        translator.registerIncomingPacketForRevision(revision, 1371, 3991); //GetBadgePointLimitsComposer
        translator.registerIncomingPacketForRevision(revision, 1364, 1728);//GetIsBadgeRequestFulfilled
        translator.registerIncomingPacketForRevision(revision, 3077, 368);//RequestABadge
        translator.registerIncomingPacketForRevision(revision, 644, 1167);//SetActivatedBadges
        translator.registerIncomingPacketForRevision(revision, 2769, 2209);//GetBadges

        //inventory.bots
        translator.registerIncomingPacketForRevision(revision, 3848, 953);//GetBotInventory

        //inventory.furni
        translator.registerIncomingPacketForRevision(revision, 3150, 1467);//Unknown_1467 ?? RequestFurniInventory
        //translator.registerIncomingPacketForRevision(revision, 0, 3974);//Unknown_3974
        //translator.registerIncomingPacketForRevision(revision, 0, 783);//Unknown_783

        //inventory.pets
        translator.registerIncomingPacketForRevision(revision, 2713, 896);//CancelPetBreeding
        translator.registerIncomingPacketForRevision(revision, 3382, 3823);//ConfirmPetBreeding
        translator.registerIncomingPacketForRevision(revision, Events.GetPetInventoryMessageEvent, 561);//GetPetInventory

        //inventory.purse
        translator.registerIncomingPacketForRevision(revision, Events.RequestUserCreditsMessageEvent, 1441);

        //inventory.trade
        translator.registerIncomingPacketForRevision(revision, 3107, 1735);//AddItemToTrade
        translator.registerIncomingPacketForRevision(revision, 1481, 520);//OpenTrading
        translator.registerIncomingPacketForRevision(revision, 3845, 2059);//RemoveItemFromTrade
        //translator.registerIncomingPacketForRevision(revision, 0, 1884);//Unknown_1884
        //translator.registerIncomingPacketForRevision(revision, 0, 2000);//Unknown_2000
        //translator.registerIncomingPacketForRevision(revision, 0, 593);//Unknown_593
        //translator.registerIncomingPacketForRevision(revision, 0, 842);//Unknown_842
        //translator.registerIncomingPacketForRevision(revision, 0, 88);//Unknown_88

        //landingview.votes
        translator.registerIncomingPacketForRevision(revision, 3536, 3938);//CommunityGoalVote

        //marketplace
        translator.registerIncomingPacketForRevision(revision, 1603, 736);//BuyMarketplaceOffer
        translator.registerIncomingPacketForRevision(revision, 434, 396);//CancelMarketplaceOffer
        translator.registerIncomingPacketForRevision(revision, 3288, 2031);//GetMarketplaceItemStats
        translator.registerIncomingPacketForRevision(revision, 2407, 1111);//GetMarketplaceOffers
        translator.registerIncomingPacketForRevision(revision, 2105, 731);//GetMarketplaceOwnOffers
        translator.registerIncomingPacketForRevision(revision, 3447, 468);//MakeOffer
        translator.registerIncomingPacketForRevision(revision, 2650, 1644);//RedeemMarketplaceOfferCredits
        //translator.registerIncomingPacketForRevision(revision, 0, 1350);//Unknown_1350
        //translator.registerIncomingPacketForRevision(revision, 0, 1868);//Unknown_1868
        //translator.registerIncomingPacketForRevision(revision, 0, 2277);//Unknown_2277

        //moderator
        translator.registerIncomingPacketForRevision(revision, 2717, 2090);//CloseIssueDefaultAction
        translator.registerIncomingPacketForRevision(revision, 2067, 1928);//CloseIssues
        translator.registerIncomingPacketForRevision(revision, 211, 1153);//GetCfhChatlog
        translator.registerIncomingPacketForRevision(revision, 707,1115);//GetModeratorRoomInfo
        translator.registerIncomingPacketForRevision(revision, 3295, 3426);//GetModeratorUserInfo
        translator.registerIncomingPacketForRevision(revision, 2587, 3821);//GetRoomChatlog
        translator.registerIncomingPacketForRevision(revision, 3526, 2942);//GetRoomVisits
        translator.registerIncomingPacketForRevision(revision, 1391, 3509);//GetUserChatlog
        translator.registerIncomingPacketForRevision(revision, 229, 820);//ModAlert
        translator.registerIncomingPacketForRevision(revision, Events.ModerationBanMessageEvent, 69);//ModBan
        translator.registerIncomingPacketForRevision(revision, 3260, 234);//ModerateRoom
        translator.registerIncomingPacketForRevision(revision, 3842, 571);//ModeratorAction
        translator.registerIncomingPacketForRevision(revision, 2582, 3295);//ModKick
        translator.registerIncomingPacketForRevision(revision, 1840, 913);//ModMessage
        translator.registerIncomingPacketForRevision(revision, 1945, 3950);//ModMute
        translator.registerIncomingPacketForRevision(revision, 31, 2357);//ModToolPreferences
        translator.registerIncomingPacketForRevision(revision, 1392, 3307);//ModToolSanction
        translator.registerIncomingPacketForRevision(revision, 3742, 307);//ModTradingLock
        translator.registerIncomingPacketForRevision(revision, 15, 3528);//PickIssues
        translator.registerIncomingPacketForRevision(revision, 1572, 2696);//ReleaseIssues

        //mysterybox
        translator.registerIncomingPacketForRevision(revision, 2012, 1485);//MysteryBoxWaitingCanceled

        //navigator
        translator.registerIncomingPacketForRevision(revision, Events.GetUserFlatCatsMessageEvent, 157);//GetUserFlatCats
        translator.registerIncomingPacketForRevision(revision, Events.GetGuestRoomMessageEvent, 3622);//GetGuestRoom
        translator.registerIncomingPacketForRevision(revision, Events.RequestMyRoomsMessageEvent, 1633);//MyRoomsSearch
        translator.registerIncomingPacketForRevision(revision, Events.SearchRoomsMyFavoriteMessageEvent, 2364);//MyFavouriteRoomsSearch
        translator.registerIncomingPacketForRevision(revision, Events.SearchRoomsVisitedMessageEvent, 2048);//MyRoomHistorySearch
        translator.registerIncomingPacketForRevision(revision, Events.RequestPopularRoomsMessageEvent, 2461);//PopularRoomsSearch
        translator.registerIncomingPacketForRevision(revision, Events.RequestHighestScoreRoomsMessageEvent, 1763);//RoomsWithHighestScoreSearch
        translator.registerIncomingPacketForRevision(revision, 2809, 1011); //RoomAdSearch
        translator.registerIncomingPacketForRevision(revision, Events.RequestPublicRoomsMessageEvent, 2261);//GetOfficialRooms
        translator.registerIncomingPacketForRevision(revision, Events.AddFavouriteRoomMessageEvent, 1318);//AddFavouriteRoom
        translator.registerIncomingPacketForRevision(revision, Events.DeleteFavouriteRoomMessageEvent, 2640);//DeleteFavouriteRoom
        translator.registerIncomingPacketForRevision(revision, Events.UpdateNavigatorSettingsMessageEvent, 2170);//UpdateHomeRoom
        translator.registerIncomingPacketForRevision(revision, 2725, 1835);//CancelEvent
        translator.registerIncomingPacketForRevision(revision, 433, 3171);//CompetitionRoomsSearch
        translator.registerIncomingPacketForRevision(revision, 314, 2816);//ConvertGlobalRoomId
        translator.registerIncomingPacketForRevision(revision, 2752,2079);//CreateFlat
        translator.registerIncomingPacketForRevision(revision, 3991, 1374);//EditEvent
        translator.registerIncomingPacketForRevision(revision, 10, 1806);//ForwardToARandomPromotedRoom
        translator.registerIncomingPacketForRevision(revision, 1703, 1926);//ForwardToSomeRoom
        translator.registerIncomingPacketForRevision(revision, 3782, 466);//GetCategoriesWithUserCount
        translator.registerIncomingPacketForRevision(revision, 826, 274);//GetPopularRoomTags
        translator.registerIncomingPacketForRevision(revision, 2930, 2847);//GuildBaseSearch
        translator.registerIncomingPacketForRevision(revision, 1002, 3779);//MyFrequentRoomHistorySearch
        translator.registerIncomingPacketForRevision(revision, 2266, 1954);//MyFriendsRoomsSearch
        translator.registerIncomingPacketForRevision(revision, 39, 2850);//MyGuildBasesSearch
        translator.registerIncomingPacketForRevision(revision, 2537, 432);//MyRecommendedRooms
        translator.registerIncomingPacketForRevision(revision, 272, 784);//MyRoomRightsSearch
        translator.registerIncomingPacketForRevision(revision, 3582, 501);//RateFlat
        translator.registerIncomingPacketForRevision(revision, 3182, 2050);//RemoveOwnRoomRightsRoom
        translator.registerIncomingPacketForRevision(revision, 2412, 3206);//RoomAdEventTabAdClicked
        translator.registerIncomingPacketForRevision(revision, 1786, 2308);//RoomsWhereMyFriendsAreSearch
        translator.registerIncomingPacketForRevision(revision, 3943, 128);//RoomTextSearch
        translator.registerIncomingPacketForRevision(revision, 3305, 1992);//SetRoomSessionTags
        translator.registerIncomingPacketForRevision(revision, 1918, 2394);//ToggleStaffPick
        translator.registerIncomingPacketForRevision(revision, 2468, 1829);//UpdateRoomThumbnail
        translator.registerIncomingPacketForRevision(revision, Events.CanCreateRoomMessageEvent, 2651);//CanCreateRoom
        translator.registerIncomingPacketForRevision(revision, 1782, 158);//GetUserEventCats
        //translator.registerIncomingPacketForRevision(revision, 0, 2280);//Unknown_2280

        //Newnavigator
        translator.registerIncomingPacketForRevision(revision, 1834, 1578);//NavigatorAddCollapsedCategory
        translator.registerIncomingPacketForRevision(revision, 2226, 2251);//NavigatorAddSavedSearch
        translator.registerIncomingPacketForRevision(revision, 1954, 2929);//NavigatorDeleteSavedSearch
        translator.registerIncomingPacketForRevision(revision, 637, 3296);//NavigatorRemoveCollapsedCategory
        translator.registerIncomingPacketForRevision(revision, 1202, 3947);//NavigatorSetSearchCodeViewMode
        translator.registerIncomingPacketForRevision(revision, 249, 198);//NewNavigatorSearch
        translator.registerIncomingPacketForRevision(revision, 2110, 1063);//NewNavigatorInitComposer

        //Notifications
        translator.registerIncomingPacketForRevision(revision, 3493, 2039);//ResetUnseenItemIds
        translator.registerIncomingPacketForRevision(revision, 2343, 1333);//ResetUnseenItems

        //Nux
        translator.registerIncomingPacketForRevision(revision, 1822, 471);//NewUserExperienceGetGifts
        translator.registerIncomingPacketForRevision(revision, 1299, 1842);//NewUserExperienceScriptProceed

        //Poll
        translator.registerIncomingPacketForRevision(revision, 3505, 2830);//PollAnswer
        translator.registerIncomingPacketForRevision(revision, 1773, 2312);//PollReject
        translator.registerIncomingPacketForRevision(revision, 109, 1957);//PollStart

        //Preferences
        translator.registerIncomingPacketForRevision(revision, 1262, 927);//SetChatPreferences
        translator.registerIncomingPacketForRevision(revision, 1030, 74);//SetChatStylePreference
        translator.registerIncomingPacketForRevision(revision, 1086, 1186);//SetIgnoreRoomInvites
        translator.registerIncomingPacketForRevision(revision, 3159, 1930);//SetNewNavigatorWindowPreferences
        translator.registerIncomingPacketForRevision(revision, 1461, 1039);//SetRoomCameraPreferences
        translator.registerIncomingPacketForRevision(revision, 1367,3672);//SetSoundSettings
        translator.registerIncomingPacketForRevision(revision, 2313, 559);//SetUIFlags

        //todo: Quest

        //Register
        translator.registerIncomingPacketForRevision(revision, 2730, 2334);//UpdateFigureData

        //Room.Action
        translator.registerIncomingPacketForRevision(revision, 2996, 2829);//AmbassadorAlert
        translator.registerIncomingPacketForRevision(revision, 808, 2024);//AssignRights
        translator.registerIncomingPacketForRevision(revision, 1477, 2304);//BanUserWithDuration
        translator.registerIncomingPacketForRevision(revision, 1320, 1103);//RoomUserKick
        translator.registerIncomingPacketForRevision(revision, 1644, 850);//LetUserIn
        translator.registerIncomingPacketForRevision(revision, 3485, 2935);//RoomUserMute
        translator.registerIncomingPacketForRevision(revision, 2683, 1448);//RemoveAllRights
        translator.registerIncomingPacketForRevision(revision, 2064, 2415);//RemoveRights
        translator.registerIncomingPacketForRevision(revision, 992, 3609);//UnbanUserFromRoom
        translator.registerIncomingPacketForRevision(revision, 3637, 2999);//MuteAllInRoomComposer

        //Room.Avatar
        translator.registerIncomingPacketForRevision(revision, 2456, 996);//AvatarExpression
        translator.registerIncomingPacketForRevision(revision, 2228, 1844);//ChangeMotto
        translator.registerIncomingPacketForRevision(revision, 2235, 3775);//ChangePosture
        translator.registerIncomingPacketForRevision(revision, 3374, 1182);//CustomizeAvatarWithFurni
        translator.registerIncomingPacketForRevision(revision, 2080, 2204);//Dance
        translator.registerIncomingPacketForRevision(revision, 3301, 1580);//LookTo
        translator.registerIncomingPacketForRevision(revision, 2941, 3895);//PassCarryItem
        translator.registerIncomingPacketForRevision(revision, 2768, 3459);//PassCarryItemToPet
        translator.registerIncomingPacketForRevision(revision, 1975, 3029);//Sign
        translator.registerIncomingPacketForRevision(revision, 2814, 209);//DropCarryItem

        //Room.Bots
        translator.registerIncomingPacketForRevision(revision, 2624, 2650);//CommandBot
        translator.registerIncomingPacketForRevision(revision, 1986, 2615);//GetBotCommandConfigurationData

        //Room.Chat
        translator.registerIncomingPacketForRevision(revision, Events.StartTypingMessageEvent, 3405);//StartTyping
        translator.registerIncomingPacketForRevision(revision, Events.CancelTypingMessageEvent, 1132);//CancelTyping
        translator.registerIncomingPacketForRevision(revision, Events.ChatMessageEvent, 3876);//Chat
        translator.registerIncomingPacketForRevision(revision, Events.ShoutMessageEvent, 1646);//Shout
        translator.registerIncomingPacketForRevision(revision, Events.WhisperMessageEvent, 1950);//Whisper

        //Room.Engine
        translator.registerIncomingPacketForRevision(revision, Events.GetRoomEntryDataMessageEvent, 3109);//GetRoomEntryData
        translator.registerIncomingPacketForRevision(revision, Events.GetFurnitureAliasesMessageEvent, 445);//GetFurnitureAliases
        translator.registerIncomingPacketForRevision(revision, Events.MoveAvatarMessageEvent, 1442);//MoveAvatar
        translator.registerIncomingPacketForRevision(revision, 3964, 2817);//GetItemData
        translator.registerIncomingPacketForRevision(revision, 2161, 1738);//GetPetCommands
        translator.registerIncomingPacketForRevision(revision, 749, 1117);//GiveSupplementToPet
        translator.registerIncomingPacketForRevision(revision, 1036, 3872);//MountPet
        translator.registerIncomingPacketForRevision(revision, 248, 1251);//MoveObject
        translator.registerIncomingPacketForRevision(revision, 3449, 115);//MovePet
        translator.registerIncomingPacketForRevision(revision, 168, 2627);//MoveWallItem
        translator.registerIncomingPacketForRevision(revision, 3456, 2537);//PickupObject
        translator.registerIncomingPacketForRevision(revision, 1592, 997);//PlaceBot
        translator.registerIncomingPacketForRevision(revision, 1258, 1337);//PlaceObject
        translator.registerIncomingPacketForRevision(revision, 2647, 2570);//PlacePet
        translator.registerIncomingPacketForRevision(revision, 3323, 1579);//RemoveBotFromFlat
        translator.registerIncomingPacketForRevision(revision, 3336, 3228);//RemoveItem
        translator.registerIncomingPacketForRevision(revision, 1581, 3129);//RemovePetFromFlat
        translator.registerIncomingPacketForRevision(revision, 186, 299);//RemoveSaddleFromPet
        translator.registerIncomingPacketForRevision(revision, 924, 3446);//SetClothingChangeData
        translator.registerIncomingPacketForRevision(revision, 3666, 2882);//SetItemData
        translator.registerIncomingPacketForRevision(revision, 3608, 3256);//SetObjectData
        translator.registerIncomingPacketForRevision(revision, 3379, 2684);//TogglePetBreedingPermission
        translator.registerIncomingPacketForRevision(revision, 1472, 1440);//TogglePetRidingPermission
        translator.registerIncomingPacketForRevision(revision, 99, 1737);//UseFurniture
        translator.registerIncomingPacketForRevision(revision, 210, 3577);//UseWallItem
        //translator.registerIncomingPacketForRevision(revision, 0, 3014);//Unknown_3014
        //translator.registerIncomingPacketForRevision(revision, 0, 3050);//Unknown_3050

        //Room.Furniture
        translator.registerIncomingPacketForRevision(revision, 3283, 1238);//AddSpamWallPostIt
        translator.registerIncomingPacketForRevision(revision, 3005, 3087);//ControlYoutubeDisplayPlayback
        translator.registerIncomingPacketForRevision(revision, 3115, 846);//CreditFurniRedeem
        translator.registerIncomingPacketForRevision(revision, 1533, 2325);//DiceOff
        translator.registerIncomingPacketForRevision(revision, 2765, 3633);//EnterOneWayDoor
        translator.registerIncomingPacketForRevision(revision, 1071, 3867);//ExtendRentOrBuyoutFurni
        translator.registerIncomingPacketForRevision(revision, 2115, 3707);//ExtendRentOrBuyoutStripItem
        translator.registerIncomingPacketForRevision(revision, 2651, 1271);//GetGuildFurniContextMenuInfo
        translator.registerIncomingPacketForRevision(revision, 2518, 618);//GetRentOrBuyoutOffer
        translator.registerIncomingPacketForRevision(revision, 336, 213);//GetYoutubeDisplayStatus
        translator.registerIncomingPacketForRevision(revision, 3074, 475);//OpenMysteryTrophy
        translator.registerIncomingPacketForRevision(revision, 3698, 2956);//OpenPetPackage
        translator.registerIncomingPacketForRevision(revision, 2248, 1988);//PlacePostIt
        translator.registerIncomingPacketForRevision(revision, 3558, 205);//PresentOpen
        translator.registerIncomingPacketForRevision(revision, 1667, 3671);//RentableSpaceCancelRent
        translator.registerIncomingPacketForRevision(revision, 2946, 2624);//RentableSpaceRent
        translator.registerIncomingPacketForRevision(revision, 872, 106);//RentableSpaceStatus
        translator.registerIncomingPacketForRevision(revision, 1648, 935);//RoomDimmerSavePreset
        translator.registerIncomingPacketForRevision(revision, 3839, 3118);//SetCustomStackingHeight
        translator.registerIncomingPacketForRevision(revision, 2209, 2256);//SetMannequinFigure
        translator.registerIncomingPacketForRevision(revision, 2850, 3097);//SetMannequinName
        translator.registerIncomingPacketForRevision(revision, 3617, 993);//SetRandomState
        translator.registerIncomingPacketForRevision(revision, 2880, 2352);//SetRoomBackgroundColorData
        translator.registerIncomingPacketForRevision(revision, 2069, 1820);//SetYoutubeDisplayPlaylist
        translator.registerIncomingPacketForRevision(revision, 2144, 2146);//SpinWheelOfFortune
        translator.registerIncomingPacketForRevision(revision, 1990, 1938);//ThrowDice
        //translator.registerIncomingPacketForRevision(revision, 0, 322);//Unknown_322
        //translator.registerIncomingPacketForRevision(revision, 0, 335);//Unknown_335

        //Room.Layout
        translator.registerIncomingPacketForRevision(revision, 875, 1639);//UpdateFloorProperties
        //translator.registerIncomingPacketForRevision(revision, 0, 1511);//Unknown_1511
        //translator.registerIncomingPacketForRevision(revision, 0, 3591);//Unknown_3591
        //GetOccupiedTilesMessageComposer or GetRoomEntryTileMessageComposer

        //Room.Pets
        translator.registerIncomingPacketForRevision(revision, 1638, 2701);//BreedPets
        translator.registerIncomingPacketForRevision(revision, 1328, 2584);//CustomizePetWithFurni
        translator.registerIncomingPacketForRevision(revision, 2934, 1322);//GetPetInfo
        translator.registerIncomingPacketForRevision(revision, 549, 1481);//PetSelected
        translator.registerIncomingPacketForRevision(revision, 3202, 3762);//RespectPet

        //Room.Session
        translator.registerIncomingPacketForRevision(revision, Events.OpenFlatConnectionMessageEvent, 61);//OpenFlatConnection
        translator.registerIncomingPacketForRevision(revision, 3093, 87);//ChangeQueue
        translator.registerIncomingPacketForRevision(revision, 685, 2150);//GoToFlatMessageComposer


        //Roomdirectory
        translator.registerIncomingPacketForRevision(revision, 3736, 3944);//RoomNetworkOpenConnection

        //RoomSettings
        translator.registerIncomingPacketForRevision(revision, Events.GetRoomSettingsMessageEvent, 1791);//GetRoomSettings
        translator.registerIncomingPacketForRevision(revision, Events.SaveRoomSettingsMessageEvent, 3383);//SaveRoomSettings
        translator.registerIncomingPacketForRevision(revision, 532, 2463);//DeleteRoom
        translator.registerIncomingPacketForRevision(revision, 2267, 120);//GetBannedUsersFromRoom
        translator.registerIncomingPacketForRevision(revision, 1911, 3376);//GetCustomRoomFilter
        translator.registerIncomingPacketForRevision(revision, 3385, 2607);//GetFlatControllers
        translator.registerIncomingPacketForRevision(revision, 1265, 455);//UpdateRoomCategoryAndTradeSettings
        translator.registerIncomingPacketForRevision(revision, 3001, 1099);//UpdateRoomFilter

        //Sound
        translator.registerIncomingPacketForRevision(revision, 753, 941);//AddJukeboxDisk
        translator.registerIncomingPacketForRevision(revision, 1435, 1893);//GetJukeboxPlayList
        translator.registerIncomingPacketForRevision(revision, 1325, 262);//GetNowPlaying
        translator.registerIncomingPacketForRevision(revision, 3189, 969);//GetOfficialSongId
        translator.registerIncomingPacketForRevision(revision, 3082, 2153);//GetSongInfo
        translator.registerIncomingPacketForRevision(revision, 3498, 2405);//GetSoundMachinePlayList
        translator.registerIncomingPacketForRevision(revision, 2388, 391);//GetSoundSettings
        translator.registerIncomingPacketForRevision(revision, 2304, 728);//GetUserSongDisks
        translator.registerIncomingPacketForRevision(revision, 3050, 2666);//RemoveJukeboxDisk

        //Talent
        translator.registerIncomingPacketForRevision(revision, 196, 3217);//GetTalentTrack
        translator.registerIncomingPacketForRevision(revision, 2127, 1317);//GetTalentTrackLevel
        translator.registerIncomingPacketForRevision(revision, 2455, 1156);//GuideAdvertisementRead

        //Tracking
        translator.registerIncomingPacketForRevision(revision, 295, 2808);//LatencyPingRequest
        translator.registerIncomingPacketForRevision(revision, 96, 518);//LatencyPingReport
        translator.registerIncomingPacketForRevision(revision, 3457, 1278);//EventLog
        translator.registerIncomingPacketForRevision(revision, 3847, 143);//LagWarningReport
        translator.registerIncomingPacketForRevision(revision, 3230, 2023);//PerformanceLog
        //translator.registerIncomingPacketForRevision(revision, 0, 2434);//PerformanceTabletLog

        //Userclassification
        translator.registerIncomingPacketForRevision(revision, 1160, 226);//PeerUsersClassification
        translator.registerIncomingPacketForRevision(revision, 2285, 2260);//RoomUsersClassification

        //Userdefinedroomevents
        translator.registerIncomingPacketForRevision(revision, 3373, 3392);//ApplySnapshot
        translator.registerIncomingPacketForRevision(revision, 768, 1176);//Open
        translator.registerIncomingPacketForRevision(revision, 2281, 2211);//UpdateAction
        translator.registerIncomingPacketForRevision(revision, 3203, 1032);//UpdateCondition
        translator.registerIncomingPacketForRevision(revision, 1520, 752);//UpdateTrigger

        //Users
        //translator.registerIncomingPacketForRevision(revision, 0, 1588);//AccountSafetyLockGetQuestions
        //translator.registerIncomingPacketForRevision(revision, 0, 2739);//AccountSafetyLockUnlock
        translator.registerIncomingPacketForRevision(revision, 2894, 1518);//AddAdminRightsToMember
        translator.registerIncomingPacketForRevision(revision, 882, 3412);//ApproveAllMembershipRequests
        translator.registerIncomingPacketForRevision(revision, 3386, 163);//ApproveMembershipRequest
        translator.registerIncomingPacketForRevision(revision, 2109, 2002);//ApproveName
        translator.registerIncomingPacketForRevision(revision, 3965, 2913);//ChangeEmail
        translator.registerIncomingPacketForRevision(revision, 230, 1625);//CreateGuild
        translator.registerIncomingPacketForRevision(revision, 1134, 3639);//DeactivateGuild
        translator.registerIncomingPacketForRevision(revision, 1820, 2730);//DeselectFavouriteHabboGroup
        translator.registerIncomingPacketForRevision(revision, 2249, 2066);//GetExtendedProfileByName
        translator.registerIncomingPacketForRevision(revision, 3265, 2313);//GetExtendedProfile
        translator.registerIncomingPacketForRevision(revision, 798, 2437);//GetGuildCreationInfo
        translator.registerIncomingPacketForRevision(revision, 1004, 2421);//GetGuildEditInfo
        translator.registerIncomingPacketForRevision(revision, 813, 1071);//GetGuildEditorData
        translator.registerIncomingPacketForRevision(revision, 312, 3056);//GetGuildMembers
        translator.registerIncomingPacketForRevision(revision, 367, 1135);//GetGuildMemberships
        translator.registerIncomingPacketForRevision(revision, 2991, 1696);//GetHabboGroupDetails
        translator.registerIncomingPacketForRevision(revision, 3878, 649);//GetIgnoredUsers
        translator.registerIncomingPacketForRevision(revision, 3593, 3034);//GetMemberGuildItemCount
        translator.registerIncomingPacketForRevision(revision, 2138, 1040);//GetRelationshipStatusInfo
        translator.registerIncomingPacketForRevision(revision, 2091, 285);//GetSelectedBadges
        translator.registerIncomingPacketForRevision(revision, 1117, 2302);//IgnoreUser
        translator.registerIncomingPacketForRevision(revision, 3314, 2071);//IgnoreUserId
        translator.registerIncomingPacketForRevision(revision, 998, 3400);//JoinHabboGroup
        translator.registerIncomingPacketForRevision(revision, 593, 379);//KickMember
        translator.registerIncomingPacketForRevision(revision, 1894, 1079);//RejectMembershipRequest
        translator.registerIncomingPacketForRevision(revision, 722, 694);//RemoveAdminRightsFromMember
        translator.registerIncomingPacketForRevision(revision, 2694, 744);//RespectUser
        translator.registerIncomingPacketForRevision(revision, 3166, 3229);//ScrGetUserInfo
        translator.registerIncomingPacketForRevision(revision, 3549, 3481);//SelectFavouriteHabboGroup
        translator.registerIncomingPacketForRevision(revision, 2061, 2058);//UnignoreUser
        translator.registerIncomingPacketForRevision(revision, 1991, 3465);//UpdateGuildBadge
        translator.registerIncomingPacketForRevision(revision, 1764, 3339);//UpdateGuildColors
        translator.registerIncomingPacketForRevision(revision, 3137, 1247);//UpdateGuildIdentity
        translator.registerIncomingPacketForRevision(revision, 3435, 1618);//UpdateGuildSettings
        //translator.registerIncomingPacketForRevision(revision, 0, 1163);//Unknown_1163
        //translator.registerIncomingPacketForRevision(revision, 0, 1558);//Unknown_1558
        //translator.registerIncomingPacketForRevision(revision, 0, 2286);//Unknown_2286
        //translator.registerIncomingPacketForRevision(revision, 0, 2767);//Unknown_2767
        //GetMOTDMessageComposer
        //ScrGetKickbackInfo
        //UnblockGroupMember

        //Unknown
        /*
        [1227] Unknown - Unknown_1227
        [1441] Unknown - Unknown_1441
        [2728] Unknown - Unknown_2728
        [ 953] Unknown - Unknown_953
         */

    }

    @Override
    public void registerOutgoing(HeaderTranslator translator) {
        //Handshake
        translator.registerOutgoingPacketForRevision(revision, 3885,1313);//CompleteDiffieHandshake
        translator.registerOutgoingPacketForRevision(revision, 1347,2945);//InitDiffieHandshake
        translator.registerOutgoingPacketForRevision(revision, 2491,1554);//AuthenticationOk
        translator.registerOutgoingPacketForRevision(revision, 1488,340);//UniqueMachineId
        translator.registerOutgoingPacketForRevision(revision, 411,995);//UserRights
        translator.registerOutgoingPacketForRevision(revision,4000, 4000);//DisconnectReason
        translator.registerOutgoingPacketForRevision(revision, 2725, 3871);//UserObject
        translator.registerOutgoingPacketForRevision(revision, 3928, 629);//Ping
        translator.registerOutgoingPacketForRevision(revision, 1600, 509);//GenericError
        translator.registerOutgoingPacketForRevision(revision, 3523, 2643);//IdentityAccounts
        translator.registerOutgoingPacketForRevision(revision, 793, 2387);//IsFirstLoginOfDay
        translator.registerOutgoingPacketForRevision(revision, 3738, 603);//NoobnessLevelMessageEvent

        //Advertisement
        translator.registerOutgoingPacketForRevision(revision, 1808, 3785);//Interstitial
        translator.registerOutgoingPacketForRevision(revision, 1759, 2422);//RoomAdError

        //Availability
        translator.registerOutgoingPacketForRevision(revision, 2033, 30);//AvailabilityStatus
        translator.registerOutgoingPacketForRevision(revision, 600,396);//AvailabilityTime
        translator.registerOutgoingPacketForRevision(revision, 1350,1627);//MaintenanceStatus
        //translator.registerOutgoingPacketForRevision(revision, 0,3426);//InfoHotelClosed
        //translator.registerOutgoingPacketForRevision(revision, 0,2366);//InfoHotelClosing
        //translator.registerOutgoingPacketForRevision(revision, 0,2224);//LoginFailedHotelClosed

        //Avatar
        translator.registerOutgoingPacketForRevision(revision, 118, 2289);//ChangeUserNameResult
        translator.registerOutgoingPacketForRevision(revision, 563, 3833);//CheckUserNameResult
        translator.registerOutgoingPacketForRevision(revision, 2429, 2862);//FigureUpdate
        translator.registerOutgoingPacketForRevision(revision, 3315, 2950);//Wardrobe

        //Callforhelp
        translator.registerOutgoingPacketForRevision(revision, 2782, 3821);//CfhSanction
        translator.registerOutgoingPacketForRevision(revision, 325, 2692);//CfhTopicsInit

        //Camera
        translator.registerOutgoingPacketForRevision(revision, 2057, 2027);//CameraPublishStatus
        translator.registerOutgoingPacketForRevision(revision, 3696, 304);//CameraStorageUrl
        translator.registerOutgoingPacketForRevision(revision, 133, 1361);//CompetitionStatus
        translator.registerOutgoingPacketForRevision(revision, 3878, 1317);//InitCamera
        translator.registerOutgoingPacketForRevision(revision, 3595, 919);//ThumbnailStatus
        translator.registerOutgoingPacketForRevision(revision, 2783, 2628);//CameraPurchaseOKMessageEvent

        //Campaign
        translator.registerOutgoingPacketForRevision(revision, 2531, 2265);//CampaignCalendarData
        translator.registerOutgoingPacketForRevision(revision, 2551, 1641);//CampaignCalendarDoorOpened

        //Catalog
        translator.registerOutgoingPacketForRevision(revision, 1533, 1006);//BonusRareInfo
        translator.registerOutgoingPacketForRevision(revision, 3828, 349);//BuildersClubFurniCount
        translator.registerOutgoingPacketForRevision(revision, 1452, 131);//BuildersClubSubscriptionStatus
        translator.registerOutgoingPacketForRevision(revision, 2347, 1952);//BundleDiscountRuleset
        translator.registerOutgoingPacketForRevision(revision, 1032, 3027);//CatalogIndex
        translator.registerOutgoingPacketForRevision(revision, 804, 1857);//CatalogPage
        translator.registerOutgoingPacketForRevision(revision, 2668, 3269);//CatalogPageExpiration
        translator.registerOutgoingPacketForRevision(revision, 2515, 66);//CatalogPageWithEarliestExpiry
        translator.registerOutgoingPacketForRevision(revision, 1866, 1257);//CatalogPublished
        translator.registerOutgoingPacketForRevision(revision, 619, 2923);//ClubGiftInfo
        translator.registerOutgoingPacketForRevision(revision, 659, 3735);//ClubGiftSelected
        translator.registerOutgoingPacketForRevision(revision, 195, 506);//DirectSMSClubBuyAvailable
        translator.registerOutgoingPacketForRevision(revision, 2234, 1756);//GiftWrappingConfiguration
        translator.registerOutgoingPacketForRevision(revision, 3964, 1117);//HabboClubExtendOffer
        translator.registerOutgoingPacketForRevision(revision, 2405, 3055);//HabboClubOffers
        translator.registerOutgoingPacketForRevision(revision, 761, 272);//IsOfferGiftable
        translator.registerOutgoingPacketForRevision(revision, 44, 303);//LimitedOfferAppearingNext
        translator.registerOutgoingPacketForRevision(revision, 3914, 3866);//NotEnoughBalance
        translator.registerOutgoingPacketForRevision(revision, 3388, 3467);//ProductOffer
        translator.registerOutgoingPacketForRevision(revision, 1404, 3809);//PurchaseError
        translator.registerOutgoingPacketForRevision(revision, 3770, 972);//PurchaseNotAllowed
        translator.registerOutgoingPacketForRevision(revision, 869, 3025);//PurchaseOK
        translator.registerOutgoingPacketForRevision(revision, 2468, 1134);//RoomAdPurchaseInfoEvent
        translator.registerOutgoingPacketForRevision(revision, 1889, 1925);//SeasonalCalendarDailyOffer
        translator.registerOutgoingPacketForRevision(revision, 3331, 1481);//SellablePetPalettes
        //translator.registerOutgoingPacketForRevision(revision, 119, 198);//TargetedOffer
        //translator.registerOutgoingPacketForRevision(revision, 0, 2555);//TargetedOfferList
        translator.registerOutgoingPacketForRevision(revision, 3336, 3133);//VoucherRedeemOk
        translator.registerOutgoingPacketForRevision(revision, 714, 1378);//VoucherRedeemError
        //translator.registerOutgoingPacketForRevision(revision, 0, 1579);//Unknown_1579
        //translator.registerOutgoingPacketForRevision(revision, 0, 3161);//Unknown_3161
        //translator.registerOutgoingPacketForRevision(revision, 0, 3556);//Unknown_3556

        //Competition
        translator.registerOutgoingPacketForRevision(revision, 1745, 3667);//CurrentTimingCode
        translator.registerOutgoingPacketForRevision(revision, 1177, 2662);//CompetitionEntrySubmitResult
        translator.registerOutgoingPacketForRevision(revision, 3506, 864);//CompetitionVotingInfo
        translator.registerOutgoingPacketForRevision(revision, 3841, 2973);//IsUserPartOfCompetition
        translator.registerOutgoingPacketForRevision(revision, 3926, 1949);//SecondsUntil
        translator.registerOutgoingPacketForRevision(revision, 2064, 713);//NoOwnedRoomsAlertMessageEvent

        //Crafting
        translator.registerOutgoingPacketForRevision(revision, 1000, 3919);//CraftableProducts
        translator.registerOutgoingPacketForRevision(revision, 2774, 3509);//CraftingRecipe
        translator.registerOutgoingPacketForRevision(revision, 2124, 3780);//CraftingRecipesAvailable
        translator.registerOutgoingPacketForRevision(revision, 618, 2280);//CraftingResult

        //Error
        translator.registerOutgoingPacketForRevision(revision, 1004, 2922);//ErrorReport

        //Friendfurni
        translator.registerOutgoingPacketForRevision(revision, 770, 1996);//FriendFurniCancelLock
        translator.registerOutgoingPacketForRevision(revision, 382, 2604);//FriendFurniOtherLockConfirmed
        translator.registerOutgoingPacketForRevision(revision, 3753, 3104);//FriendFurniStartConfirmation

        //Friendlist
        translator.registerOutgoingPacketForRevision(revision, 896, 1055);//AcceptFriendResult
        translator.registerOutgoingPacketForRevision(revision, 1210, 3587);//FindFriendsProcessResult
        translator.registerOutgoingPacketForRevision(revision, 3048, 1258);//FollowFriendFailed
        translator.registerOutgoingPacketForRevision(revision, 2800, 491);//FriendListUpdate
        translator.registerOutgoingPacketForRevision(revision, 3082,1600 );//FriendNotification
        translator.registerOutgoingPacketForRevision(revision, 280, 1083);//FriendRequests
        translator.registerOutgoingPacketForRevision(revision, 3130, 3194);//FriendsListFragment
        translator.registerOutgoingPacketForRevision(revision, 973, 435);//HabboSearchResult
        translator.registerOutgoingPacketForRevision(revision, 3359, 3752);//InstantMessageError
        translator.registerOutgoingPacketForRevision(revision, 892, 89);//MessengerError
        translator.registerOutgoingPacketForRevision(revision, 1605, 1136);//MessengerInit
        translator.registerOutgoingPacketForRevision(revision, 2803, 1101);//MiniMailUnreadCount
        translator.registerOutgoingPacketForRevision(revision, 1587, 984);//NewConsoleMessage
        translator.registerOutgoingPacketForRevision(revision, 2219, 2881);//NewFriendRequest
        translator.registerOutgoingPacketForRevision(revision, 3870, 735);//RoomInvite
        translator.registerOutgoingPacketForRevision(revision, 462, 2263);//RoomInviteError
        translator.registerOutgoingPacketForRevision(revision, 1911, 924);//MiniMailNewMessageEvent

        //todo: Game.Directory
        //todo: Game.Lobby
        //todo: Game.score

        //Gifts
        translator.registerOutgoingPacketForRevision(revision, 2890, 3918);//PhoneCollectionState
        translator.registerOutgoingPacketForRevision(revision, 800, 1643);//TryPhoneNumberResult
        translator.registerOutgoingPacketForRevision(revision, 91, 1533);//TryVerificationCodeResult

        //Groupforums
        translator.registerOutgoingPacketForRevision(revision, 3011, 3084);//ForumData
        translator.registerOutgoingPacketForRevision(revision, 1073, 1116);//GuildForumThreads
        translator.registerOutgoingPacketForRevision(revision, 3001, 277);//ForumsList
        translator.registerOutgoingPacketForRevision(revision, 2049, 376);//PostMessage
        translator.registerOutgoingPacketForRevision(revision, 1862, 3094);//PostThread
        translator.registerOutgoingPacketForRevision(revision, 509, 671);//ThreadMessages
        translator.registerOutgoingPacketForRevision(revision, 2379, 1243);//UnreadForumsCount
        translator.registerOutgoingPacketForRevision(revision, 324, 3234);//UpdateMessage
        translator.registerOutgoingPacketForRevision(revision, 2528, 1114);//UpdateThread

        //Help
        translator.registerOutgoingPacketForRevision(revision, 1651, 3579);//CallForHelpDisabledNotify
        translator.registerOutgoingPacketForRevision(revision, 1121, 3723);//CallForHelpPendingCalls
        translator.registerOutgoingPacketForRevision(revision, 3796, 3993);//CallForHelpReply
        translator.registerOutgoingPacketForRevision(revision, 3635, 3062);//CallForHelpResult
        translator.registerOutgoingPacketForRevision(revision, 735, 1956);//ChatReviewSessionOfferedToGuide
        translator.registerOutgoingPacketForRevision(revision, 3276, 2712);//ChatReviewSessionResults
        translator.registerOutgoingPacketForRevision(revision, 143, 3881);//ChatReviewSessionStarted
        translator.registerOutgoingPacketForRevision(revision, 1829, 568);//ChatReviewSessionVotingStatus
        translator.registerOutgoingPacketForRevision(revision, 2819, 151);//FaqCategory
        translator.registerOutgoingPacketForRevision(revision, 2494, 2915);//FaqClientFaqs
        translator.registerOutgoingPacketForRevision(revision, 3292, 3091);//FaqText
        translator.registerOutgoingPacketForRevision(revision, 1548, 3640);//GuideOnDutyStatus
        translator.registerOutgoingPacketForRevision(revision, 3463,1382 );//GuideReportingStatus
        translator.registerOutgoingPacketForRevision(revision, 1591, 3452);//GuideSessionAttached
        translator.registerOutgoingPacketForRevision(revision, 1456, 1472);//GuideSessionEnded
        translator.registerOutgoingPacketForRevision(revision, 673, 3534);//GuideSessionError
        translator.registerOutgoingPacketForRevision(revision, 219, 2006);//GuideSessionInvitedToGuideRoom
        translator.registerOutgoingPacketForRevision(revision, 841, 3215);//GuideSessionMessage
        translator.registerOutgoingPacketForRevision(revision, 1016, 2133);//GuideSessionPartnerIsTyping
        translator.registerOutgoingPacketForRevision(revision, 1847, 675);//GuideSessionRequesterRoom
        translator.registerOutgoingPacketForRevision(revision, 3209, 692);//GuideSessionStarted
        translator.registerOutgoingPacketForRevision(revision, 3285, 3450);//GuideTicketCreationResult
        translator.registerOutgoingPacketForRevision(revision, 2674, 327);//GuideTicketResolution
        translator.registerOutgoingPacketForRevision(revision, 934, 3065);//IssueCloseNotification
        translator.registerOutgoingPacketForRevision(revision, 2927, 3793);//QuizData
        translator.registerOutgoingPacketForRevision(revision, 2772, 1928);//QuizResults
        //translator.registerOutgoingPacketForRevision(revision, 0, 3979);//FaqCategories
        //translator.registerOutgoingPacketForRevision(revision, 0, 2865);//FaqSearchResults
        //translator.registerOutgoingPacketForRevision(revision, 0, 1452);//Unknown_1452
        //translator.registerOutgoingPacketForRevision(revision, 0, 2821);//Unknown_2821
        //translator.registerOutgoingPacketForRevision(revision, 0, 3726);//Unknown_3726
        //translator.registerOutgoingPacketForRevision(revision, 0, 876);//Unknown_876

        //Help.Data
        //translator.registerOutgoingPacketForRevision(revision, 0, 2382);//CallForHelpCreated

        //Inventory.Achievements
        translator.registerOutgoingPacketForRevision(revision, 2107, 947);//Achievement
        translator.registerOutgoingPacketForRevision(revision, 305, 451);//Achievements
        translator.registerOutgoingPacketForRevision(revision, 1968, 1848);//AchievementsScore

        //Inventory.Avatareffect
        translator.registerOutgoingPacketForRevision(revision, 1959, 601);//AvatarEffectActivated
        translator.registerOutgoingPacketForRevision(revision, 2867, 1726);//AvatarEffectAdded
        translator.registerOutgoingPacketForRevision(revision, 2228, 60);//AvatarEffectExpired
        translator.registerOutgoingPacketForRevision(revision, 340, 3022);//AvatarEffects
        translator.registerOutgoingPacketForRevision(revision, 3473, 315);//AvatarEffectSelected

        //Inventory.Badges
        translator.registerOutgoingPacketForRevision(revision, 2501, 1604);//BadgePointLimits
        translator.registerOutgoingPacketForRevision(revision, 2493, 2154);//BadgeReceived
        //translator.registerOutgoingPacketForRevision(revision, 717, 2882);//Badges todo:this dcs
        translator.registerOutgoingPacketForRevision(revision, 2998, 3561);//IsBadgeRequestFulfilled

        //Inventory.Bots
        translator.registerOutgoingPacketForRevision(revision, 1352, 2216);//BotAddedToInventory
        translator.registerOutgoingPacketForRevision(revision, 3086, 1672);//BotInventory
        translator.registerOutgoingPacketForRevision(revision, 3684, 3335);//BotReceived
        translator.registerOutgoingPacketForRevision(revision, 233, 590);//BotRemovedFromInventory

        //Inventory.Clothing
        translator.registerOutgoingPacketForRevision(revision, 1450, 1950);//FigureSetIds
        //translator.registerOutgoingPacketForRevision(revision, 0, 622);//FigureSetIdAdded
        //translator.registerOutgoingPacketForRevision(revision, 0, 385);//FigureSetIdRemoved

        //Inventory.Furni
        translator.registerOutgoingPacketForRevision(revision, 994, 3907);//FurniList
        translator.registerOutgoingPacketForRevision(revision, 159, 2352);//FurniListRemove
        translator.registerOutgoingPacketForRevision(revision, 1501, 334);//PostItPlaced
        translator.registerOutgoingPacketForRevision(revision, 104, 1794);//FurniListAddOrUpdateEvent
        translator.registerOutgoingPacketForRevision(revision, 3151, 336);//FurniListInvalidateEvent

        //Inventory.Pets
        translator.registerOutgoingPacketForRevision(revision, 634, 1591);//ConfirmBreedingRequest
        translator.registerOutgoingPacketForRevision(revision, 1625, 64);//ConfirmBreedingResult
        translator.registerOutgoingPacketForRevision(revision, 2621, 2575);//GoToBreedingNestFailure
        translator.registerOutgoingPacketForRevision(revision, 2527, 3796);//NestBreedingSuccess
        translator.registerOutgoingPacketForRevision(revision, 2101, 3464);//PetAddedToInventory
        translator.registerOutgoingPacketForRevision(revision, 1746, 2911);//PetBreeding
        translator.registerOutgoingPacketForRevision(revision, 3522, 2902);//PetInventory
        translator.registerOutgoingPacketForRevision(revision, 1111, 3114);//PetReceived
        translator.registerOutgoingPacketForRevision(revision, 3253, 2126);//PetRemovedFromInventory

        //Inventory.Purse
        translator.registerOutgoingPacketForRevision(revision, 3475, 1609);//CreditBalance

        //Inventory.Trading
        translator.registerOutgoingPacketForRevision(revision, 217, 3962);//TradingOpenFailed
        translator.registerOutgoingPacketForRevision(revision, 2568, 3420);//TradingAccept
        translator.registerOutgoingPacketForRevision(revision, 1373, 2897);//TradingClose
        translator.registerOutgoingPacketForRevision(revision, 2024, 2284);//TradingItemList
        translator.registerOutgoingPacketForRevision(revision, 2505, 3923);//TradingOpen
        //translator.registerOutgoingPacketForRevision(revision, 0, 2587);//Unknown_2587
        //translator.registerOutgoingPacketForRevision(revision, 0, 2875);//Unknown_2875
        //translator.registerOutgoingPacketForRevision(revision, 0, 3977);//Unknown_3977
        //translator.registerOutgoingPacketForRevision(revision, 0, 658);//Unknown_658
        //translator.registerOutgoingPacketForRevision(revision, 0, 674);//Unknown_674
        //translator.registerOutgoingPacketForRevision(revision, 0, 871);//Unknown_871

        //Landingview
        translator.registerOutgoingPacketForRevision(revision, 286, 3764);//PromoArticles

        //Landingview.Votes
        translator.registerOutgoingPacketForRevision(revision, 1435, 1494);//CommunityGoalVote

        //Marketplace
        translator.registerOutgoingPacketForRevision(revision, 2032, 3392);//MarketplaceBuyOfferResult
        translator.registerOutgoingPacketForRevision(revision, 3264, 2560);//MarketplaceCancelOfferResult
        translator.registerOutgoingPacketForRevision(revision, 54, 3699);//MarketplaceCanMakeOfferResult
        translator.registerOutgoingPacketForRevision(revision, 1823, 2465);//MarketplaceConfiguration
        translator.registerOutgoingPacketForRevision(revision, 725, 214);//MarketplaceItemStats
        translator.registerOutgoingPacketForRevision(revision, 1359, 1612);//MarketplaceMakeOfferResult
        translator.registerOutgoingPacketForRevision(revision, 680, 3805);//MarketPlaceOffers
        translator.registerOutgoingPacketForRevision(revision, 3884, 3136);//MarketPlaceOwnOffers

        //Moderation
        translator.registerOutgoingPacketForRevision(revision, 607, 3774);//CfhChatlog
        translator.registerOutgoingPacketForRevision(revision, 3192, 2046);//IssueDeleted
        translator.registerOutgoingPacketForRevision(revision, 3609, 2958);//IssueInfo
        translator.registerOutgoingPacketForRevision(revision, 3150, 1624);//IssuePickFailed
        translator.registerOutgoingPacketForRevision(revision, 1890, 935);//ModeratorCaution
        translator.registerOutgoingPacketForRevision(revision, 2030, 2607);//Moderator
        translator.registerOutgoingPacketForRevision(revision, 2335, 1154);//ModeratorActionResult
        translator.registerOutgoingPacketForRevision(revision, 2696, 1483);//ModeratorInit
        translator.registerOutgoingPacketForRevision(revision, 1333, 1780);//ModeratorRoomInfo
        translator.registerOutgoingPacketForRevision(revision, 1576, 3687);//ModeratorToolPreferences
        translator.registerOutgoingPacketForRevision(revision, 2866, 2398);//ModeratorUserInfo
        translator.registerOutgoingPacketForRevision(revision, 3434, 3039);//RoomChatlog
        translator.registerOutgoingPacketForRevision(revision, 1752, 687);//RoomVisits
        translator.registerOutgoingPacketForRevision(revision, 1683, 3011);//UserBanned
        translator.registerOutgoingPacketForRevision(revision, 3377, 1646);//UserChatlog

        //Mysterybox
        translator.registerOutgoingPacketForRevision(revision, 3712, 3378);//GotMysteryBoxPrize
        translator.registerOutgoingPacketForRevision(revision, 2833, 1475);//MysteryBoxKeys
        //translator.registerOutgoingPacketForRevision(revision, 0, 1753);//Unknown_1753
        //translator.registerOutgoingPacketForRevision(revision, 0, 1779);//Unknown_1779

        //Navigator
        translator.registerOutgoingPacketForRevision(revision, 378, 162);//CanCreateRoom
        translator.registerOutgoingPacketForRevision(revision, 2599, 2053);//CanCreateRoomEvent
        translator.registerOutgoingPacketForRevision(revision, 1455, 3665);//CategoriesWithVisitorCount
        translator.registerOutgoingPacketForRevision(revision, 3954, 1883);//CompetitionRoomsData
        translator.registerOutgoingPacketForRevision(revision, 1331, 3170);//ConvertedRoomId
        translator.registerOutgoingPacketForRevision(revision, 2309, 1451);//Doorbell
        translator.registerOutgoingPacketForRevision(revision, 2524, 1929);//FavouriteChanged
        translator.registerOutgoingPacketForRevision(revision, 151, 1678);//Favourites
        translator.registerOutgoingPacketForRevision(revision, 878, 2401);//FlatAccessDenied
        translator.registerOutgoingPacketForRevision(revision, 1304, 1174);//FlatCreated
        translator.registerOutgoingPacketForRevision(revision, 687, 2142);//GetGuestRoomResult
        translator.registerOutgoingPacketForRevision(revision, 52, 672);//GuestRoomSearchResult
        translator.registerOutgoingPacketForRevision(revision, 2875, 693);//NavigatorSettings
        translator.registerOutgoingPacketForRevision(revision, 2726, 217);//OfficialRooms
        translator.registerOutgoingPacketForRevision(revision, 2012, 788);//PopularRoomTagsResult
        translator.registerOutgoingPacketForRevision(revision, 1840, 2145);//RoomEvent
        translator.registerOutgoingPacketForRevision(revision, 3297, 3892);//RoomInfoUpdated
        translator.registerOutgoingPacketForRevision(revision, 482, 2496);//RoomRating
        translator.registerOutgoingPacketForRevision(revision, 1927, 3578);//RoomThumbnailUpdateResult
        translator.registerOutgoingPacketForRevision(revision, 3244, 1517);//UserEventCats
        translator.registerOutgoingPacketForRevision(revision, 1562, 1708);//UserFlatCats
        translator.registerOutgoingPacketForRevision(revision, 3479, 562);//RoomEventCancel

        //Newnavigator
        translator.registerOutgoingPacketForRevision(revision, 1543, 2871);//CollapsedCategories
        translator.registerOutgoingPacketForRevision(revision, 3104, 309);//NavigatorLiftedRooms
        translator.registerOutgoingPacketForRevision(revision, 3052, 1038);//NavigatorMetaData
        translator.registerOutgoingPacketForRevision(revision, 3984, 925);//NavigatorSavedSearches
        translator.registerOutgoingPacketForRevision(revision, 2690, 2327);//NavigatorSearchResultBlocks
        translator.registerOutgoingPacketForRevision(revision, 518, 1636);//NewNavigatorPreferences

        //Notifications
        translator.registerOutgoingPacketForRevision(revision, 2018, 467);//ActivityPoints
        translator.registerOutgoingPacketForRevision(revision, 2188, 1581);//ClubGiftNotification
        translator.registerOutgoingPacketForRevision(revision, 1787, 1037);//ElementPointer
        translator.registerOutgoingPacketForRevision(revision, 806, 2883);//HabboAchievementNotification
        translator.registerOutgoingPacketForRevision(revision, 2275, 2861);//HabboActivityPointNotification
        translator.registerOutgoingPacketForRevision(revision, 3801, 733);//HabboBroadcast
        translator.registerOutgoingPacketForRevision(revision, 3284, 885);//InfoFeedEnable
        translator.registerOutgoingPacketForRevision(revision, 2035, 3048);//MOTDNotification
        translator.registerOutgoingPacketForRevision(revision, 1992, 2933);//NotificationDialog
        translator.registerOutgoingPacketForRevision(revision, 2125, 1669);//OfferRewardDelivered
        translator.registerOutgoingPacketForRevision(revision, 859, 3054);//PetLevelNotification
        translator.registerOutgoingPacketForRevision(revision, 2103, 2947);//UnseenItems
        translator.registerOutgoingPacketForRevision(revision, 426, 2949);//RestoreClientMessageEvent

        //Nux
        translator.registerOutgoingPacketForRevision(revision, 3575, 676);//NewUserExperienceGiftOffer
        translator.registerOutgoingPacketForRevision(revision, 3639, 33);//NewUserExperienceNotComplete

        //Perk
        translator.registerOutgoingPacketForRevision(revision, 2586, 1693);//PerkAllowances
        translator.registerOutgoingPacketForRevision(revision, 2278, 1589);//CitizenshipVipOfferPromoEnabled

        //Poll
        translator.registerOutgoingPacketForRevision(revision, 2997, 611);//PollContents
        translator.registerOutgoingPacketForRevision(revision, 3785, 2080);//PollOffer
        translator.registerOutgoingPacketForRevision(revision, 2665, 3385);//Question
        translator.registerOutgoingPacketForRevision(revision, 2589, 1144);//QuestionAnswered
        translator.registerOutgoingPacketForRevision(revision, 1066, 1358);//QuestionFinished
        translator.registerOutgoingPacketForRevision(revision, 662, 2195);//PollError

        //Preferences
        translator.registerOutgoingPacketForRevision(revision, 513, 3786);//AccountPreferences

        //todo: Quest

        //Room.Action
        translator.registerOutgoingPacketForRevision(revision, 1167, 1120);//AvatarEffect
        translator.registerOutgoingPacketForRevision(revision, 1474, 120);//CarryObject
        translator.registerOutgoingPacketForRevision(revision, 2233, 231);//Dance
        translator.registerOutgoingPacketForRevision(revision, 1631, 2749);//Expression
        translator.registerOutgoingPacketForRevision(revision, 1797, 3071);//Sleep
        translator.registerOutgoingPacketForRevision(revision, 1774, 1016);//UseObject

        //Room.Bots
        translator.registerOutgoingPacketForRevision(revision, 1618, 3592);//BotCommandConfiguration
        translator.registerOutgoingPacketForRevision(revision, 639, 2980);//BotError
        translator.registerOutgoingPacketForRevision(revision, 296, 3417);//BotForceOpenContextMenu
        translator.registerOutgoingPacketForRevision(revision, 69, 2311);//BotSkillListUpdate

        //Room.Camera
        translator.registerOutgoingPacketForRevision(revision, 463, 71);//CameraSnapshot

        //Room.Chat
        translator.registerOutgoingPacketForRevision(revision, 1446, 1160);//Chat
        translator.registerOutgoingPacketForRevision(revision, 2704, 1700);//Whisper
        translator.registerOutgoingPacketForRevision(revision, 1036, 3175);//Shout
        translator.registerOutgoingPacketForRevision(revision, 566, 3129);//FloodControl
        translator.registerOutgoingPacketForRevision(revision, 826, 634);//RemainingMutePeriod
        translator.registerOutgoingPacketForRevision(revision, 1191, 3401);//RoomChatSettings
        translator.registerOutgoingPacketForRevision(revision, 2937, 536);//RoomFilterSettings
        translator.registerOutgoingPacketForRevision(revision, 1717, 2502);//UserTyping

        //Room.Engine
        translator.registerOutgoingPacketForRevision(revision, 3403, 1584);//FavouriteMembershipUpdate
        translator.registerOutgoingPacketForRevision(revision, 1301, 1730);//FloorHeightMap
        translator.registerOutgoingPacketForRevision(revision, 1723, 2402);//FurnitureAliases
        translator.registerOutgoingPacketForRevision(revision, 2753, 841);//HeightMap
        translator.registerOutgoingPacketForRevision(revision, 558, 2665);//HeightMapUpdate
        translator.registerOutgoingPacketForRevision(revision, 2187, 1784);//ItemAdd
        translator.registerOutgoingPacketForRevision(revision, 2202, 2960);//ItemDataUpdate
        translator.registerOutgoingPacketForRevision(revision, 3208, 612);//ItemRemove
        translator.registerOutgoingPacketForRevision(revision, 1369, 1983);//Items
        translator.registerOutgoingPacketForRevision(revision, 2009, 1614);//ItemUpdate
        translator.registerOutgoingPacketForRevision(revision, 1534, 2285);//ObjectAdd
        translator.registerOutgoingPacketForRevision(revision, 2547, 1632);//ObjectDataUpdate
        translator.registerOutgoingPacketForRevision(revision, 2703, 511);//ObjectRemove
        translator.registerOutgoingPacketForRevision(revision, 1778, 2995);//Objects
        translator.registerOutgoingPacketForRevision(revision, 1453, 1729);//ObjectsDataUpdate
        translator.registerOutgoingPacketForRevision(revision, 3776, 949);//ObjectUpdate
        translator.registerOutgoingPacketForRevision(revision, 749, 154);//RoomEntryInfo
        translator.registerOutgoingPacketForRevision(revision, 2454, 1740);//RoomProperty
        translator.registerOutgoingPacketForRevision(revision, 3547, 3734);//RoomVisualizationSettings
        translator.registerOutgoingPacketForRevision(revision, 3207, 3101);//SlideObjectBundle
        translator.registerOutgoingPacketForRevision(revision, 3920, 1997);//UserChange
        translator.registerOutgoingPacketForRevision(revision, 2661, 3044);//UserRemove
        translator.registerOutgoingPacketForRevision(revision, 374, 1422);//Users
        translator.registerOutgoingPacketForRevision(revision, 1640, 3609);//UserUpdate

        //Room.Furniture
        translator.registerOutgoingPacketForRevision(revision, 2816, 2695);//CustomStackingHeightUpdate
        translator.registerOutgoingPacketForRevision(revision, 909, 1900);//CustomUserNotification
        translator.registerOutgoingPacketForRevision(revision, 3431, 1942);//DiceValue
        translator.registerOutgoingPacketForRevision(revision, 35, 3270);//FurniRentOrBuyoutOffer
        translator.registerOutgoingPacketForRevision(revision, 3293, 2944);//GuildFurniContextMenuInfo
        translator.registerOutgoingPacketForRevision(revision, 2376, 3090);//OneWayDoorStatus
        translator.registerOutgoingPacketForRevision(revision, 2380, 2992);//OpenPetPackageRequested
        translator.registerOutgoingPacketForRevision(revision, 546, 3102);//OpenPetPackageResult
        translator.registerOutgoingPacketForRevision(revision, 56, 1070);//PresentOpened
        translator.registerOutgoingPacketForRevision(revision, 1868, 2078);//RentableSpaceRentFailed
        translator.registerOutgoingPacketForRevision(revision, 2046, 83);//RentableSpaceRentOk
        translator.registerOutgoingPacketForRevision(revision, 3559, 2234);//RentableSpaceStatus
        translator.registerOutgoingPacketForRevision(revision, 2366, 88);//RequestSpamWallPostIt
        translator.registerOutgoingPacketForRevision(revision, 2710, 3282);//RoomDimmerPresets
        translator.registerOutgoingPacketForRevision(revision, 1634, 2446);//RoomMessageNotification
        translator.registerOutgoingPacketForRevision(revision, 1554, 2219);//YoutubeControlVideo
        translator.registerOutgoingPacketForRevision(revision, 1112, 663);//YoutubeDisplayPlaylists
        translator.registerOutgoingPacketForRevision(revision, 1411, 2792);//YoutubeDisplayVideo

        //Room.Layout
        translator.registerOutgoingPacketForRevision(revision, 1664, 3725);//RoomEntryTile
        translator.registerOutgoingPacketForRevision(revision, 3990, 3643);//RoomOccupiedTiles

        //Room.Permissions
        translator.registerOutgoingPacketForRevision(revision, 780, 3857);//YouAreController
        translator.registerOutgoingPacketForRevision(revision, 2392, 3246);//YouAreNotController
        translator.registerOutgoingPacketForRevision(revision, 339, 480);//YouAreOwner

        //Room.Pets
        translator.registerOutgoingPacketForRevision(revision, 1553, 3654);//PetBreedingResult
        translator.registerOutgoingPacketForRevision(revision, 1164, 2447);//PetTrainingPanel
        translator.registerOutgoingPacketForRevision(revision, 2156, 2542);//PetExperience
        translator.registerOutgoingPacketForRevision(revision, 1924, 297);//PetFigureUpdate
        translator.registerOutgoingPacketForRevision(revision, 2901, 2103);//PetInfo
        translator.registerOutgoingPacketForRevision(revision, 2824, 3422);//PetLevelUpdate
        translator.registerOutgoingPacketForRevision(revision, 2913, 2962);//PetPlacingError
        translator.registerOutgoingPacketForRevision(revision, 1130, 2292);//PetScratchFailed
        translator.registerOutgoingPacketForRevision(revision, 1907, 1072);//PetStatusUpdate

        //Room.Session
        translator.registerOutgoingPacketForRevision(revision, 899, 1631);//CantConnect
        translator.registerOutgoingPacketForRevision(revision, 3783, 199);//FlatAccessible
        translator.registerOutgoingPacketForRevision(revision, 2324, 1724);//GamePlayerValue
        translator.registerOutgoingPacketForRevision(revision, 758, 889);//OpenConnection
        translator.registerOutgoingPacketForRevision(revision, 160, 1035);//RoomForward
        translator.registerOutgoingPacketForRevision(revision, 2208, 2005);//RoomQueueStatus
        translator.registerOutgoingPacketForRevision(revision, 2031, 265);//RoomReady
        translator.registerOutgoingPacketForRevision(revision, 448, 2810);//YouArePlayingGame
        translator.registerOutgoingPacketForRevision(revision, 1033, 3817);//YouAreSpectator
        translator.registerOutgoingPacketForRevision(revision, 122, 1048);//CloseConnection

        //Roomsettings
        translator.registerOutgoingPacketForRevision(revision, 1869, 2350);//BannedUsersFromRoom
        translator.registerOutgoingPacketForRevision(revision, 2088, 3362);//FlatControllerAdded
        translator.registerOutgoingPacketForRevision(revision, 1327, 1410);//FlatControllerRemoved
        translator.registerOutgoingPacketForRevision(revision, 1284, 938);//FlatControllers
        translator.registerOutgoingPacketForRevision(revision, 2533, 3825);//MuteAllInRoom
        translator.registerOutgoingPacketForRevision(revision, 84, 908);//NoSuchFlat
        translator.registerOutgoingPacketForRevision(revision, 1498, 2912);//RoomSettingsData
        translator.registerOutgoingPacketForRevision(revision, 2897, 3670);//RoomSettingsError
        translator.registerOutgoingPacketForRevision(revision, 948, 441);//RoomSettingsSaved
        translator.registerOutgoingPacketForRevision(revision, 1555, 1836);//RoomSettingsSaveError
        translator.registerOutgoingPacketForRevision(revision, 3896, 547);//ShowEnforceRoomCategoryDialog
        translator.registerOutgoingPacketForRevision(revision, 3429, 1516);//UserUnbannedFromRoom

        //Sound
        translator.registerOutgoingPacketForRevision(revision, 34, 3728);//JukeboxSongDisks
        translator.registerOutgoingPacketForRevision(revision, 469, 3068);//NowPlaying
        translator.registerOutgoingPacketForRevision(revision, 1381, 2835);//OfficialSongId
        translator.registerOutgoingPacketForRevision(revision, 1748, 2951);//PlayList
        translator.registerOutgoingPacketForRevision(revision, 1140, 2282);//PlayListSongAdded
        translator.registerOutgoingPacketForRevision(revision, 3365, 1190);//TraxSongInfo
        translator.registerOutgoingPacketForRevision(revision, 2602, 2720);//UserSongDisksInventory
        translator.registerOutgoingPacketForRevision(revision, 105, 1984);//JukeboxPlayListFull

        //Talent
        translator.registerOutgoingPacketForRevision(revision, 638, 3424);//TalentLevelUp
        translator.registerOutgoingPacketForRevision(revision, 3406, 1470);//TalentTrack
        translator.registerOutgoingPacketForRevision(revision, 1203, 3940);//TalentTrackLevel

        //Tracking
        translator.registerOutgoingPacketForRevision(revision, 10, 1391);//LatencyPingResponse

        //Userclassification
        translator.registerOutgoingPacketForRevision(revision, 966, 223);//UserClassification

        //Userdefinedroomevents
        translator.registerOutgoingPacketForRevision(revision, 1830, 1425);//Open
        translator.registerOutgoingPacketForRevision(revision, 1434, 1308);//WiredEffectData
        translator.registerOutgoingPacketForRevision(revision, 1108, 1513);//WiredConditionData
        translator.registerOutgoingPacketForRevision(revision, 383, 420);//WiredTriggerData
        translator.registerOutgoingPacketForRevision(revision, 178, 2809);//WiredRewardResult
        translator.registerOutgoingPacketForRevision(revision, 156, 2218);//WiredValidationError
        translator.registerOutgoingPacketForRevision(revision, 1155, 825);//WiredSaved


        //Users
        //translator.registerOutgoingPacketForRevision(revision, 0, 2708);//AccountSafetyLockQuestions
        //translator.registerOutgoingPacketForRevision(revision, 0, 338);//AccountSafetyLockStatusChange
        translator.registerOutgoingPacketForRevision(revision, 1503, 1496);//ApproveName
        translator.registerOutgoingPacketForRevision(revision, 1815, 891);//ChangeEmailResult
        translator.registerOutgoingPacketForRevision(revision, 612, 725);//EmailStatusResult
        translator.registerOutgoingPacketForRevision(revision, 3898, 2477);//ExtendedProfile
        translator.registerOutgoingPacketForRevision(revision, 876, 456);//ExtendedProfileChanged
        translator.registerOutgoingPacketForRevision(revision, 1459, 2511);//GroupDetailsChanged
        translator.registerOutgoingPacketForRevision(revision, 1180, 2747);//GroupMembershipRequested
        translator.registerOutgoingPacketForRevision(revision, 2808, 3176);//GuildCreated
        translator.registerOutgoingPacketForRevision(revision, 2159, 3747);//GuildCreationInfo
        translator.registerOutgoingPacketForRevision(revision, 3988, 1448);//GuildEditFailed
        translator.registerOutgoingPacketForRevision(revision, 3965, 1352);//GuildEditInfo
        translator.registerOutgoingPacketForRevision(revision, 2238, 386);//GuildEditorData
        translator.registerOutgoingPacketForRevision(revision, 1876, 283);//GuildMemberFurniCountInHQ
        translator.registerOutgoingPacketForRevision(revision, 818, 3647);//GuildMemberMgmtFailed
        translator.registerOutgoingPacketForRevision(revision, 1200, 3047);//GuildMembers
        translator.registerOutgoingPacketForRevision(revision, 2445, 2857);//GuildMembershipRejected
        translator.registerOutgoingPacketForRevision(revision, 420, 1405);//GuildMemberships
        translator.registerOutgoingPacketForRevision(revision, 265, 2246);//GuildMembershipUpdated
        translator.registerOutgoingPacketForRevision(revision, 3129, 1252);//HabboGroupDeactivated
        translator.registerOutgoingPacketForRevision(revision, 1702, 2927);//HabboGroupDetails
        translator.registerOutgoingPacketForRevision(revision, 762, 2);//HabboGroupJoinFailed
        translator.registerOutgoingPacketForRevision(revision, 1087, 3846);//UserBadges
        translator.registerOutgoingPacketForRevision(revision, 354, 1719);//HandItemReceived
        translator.registerOutgoingPacketForRevision(revision, 207, 2661);//IgnoreResult
        translator.registerOutgoingPacketForRevision(revision, 2023, 1529);//InClientLink
        translator.registerOutgoingPacketForRevision(revision, 2788, 866);//PetRespectNotification
        translator.registerOutgoingPacketForRevision(revision, 3441, 3153);//PetSupplementedNotification
        translator.registerOutgoingPacketForRevision(revision, 2016, 1847);//RelationshipStatusInfo
        translator.registerOutgoingPacketForRevision(revision, 2815, 586);//RoomUserRespect
        translator.registerOutgoingPacketForRevision(revision, 3277, 403);//ScrSendKickbackInfo
        translator.registerOutgoingPacketForRevision(revision, 954, 2517);//ScrSendUserInfo
        translator.registerOutgoingPacketForRevision(revision, 2182, 3541);//UserNameChanged
        translator.registerOutgoingPacketForRevision(revision, 2402, 3727);//HabboGroupBadges
        translator.registerOutgoingPacketForRevision(revision, 126, 168);//IgnoredUsers
    }
}
