--#import.sql file
INSERT INTO GENRES(GENRE_ID,GENRE_NAME)VALUES("1","Death Metal");

INSERT INTO ROLES(ROLE_ID, NAME) VALUES("1", "PUBLISHER");
INSERT INTO ROLES(ROLE_ID, NAME) VALUES("2", "CONSUMER");

INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("1","Arch Enemy",10,"1","http://localhost:8888/resources/ArchEnemy/Band.jpg");
INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("2","Sabaton",10,"1","http://localhost:8888/resources/Sabaton/Band.jpg");
INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("3","Powerwolf",10,"1","http://localhost:8888/resources/Powerwolf/Band.jpg");
INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("4","Manowar",10,"1","http://localhost:8888/resources/Manowar/Band.jpg");

INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("1","Root of All Evil","1",15,"2011","1","http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("2","War Eternal","1",15,"2014","1","http://localhost:8888/resources/ArchEnemy/WarEternal/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("3","Will To Power","1",15,"2017","1","http://localhost:8888/resources/ArchEnemy/WillToPower/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("4","Rise Of The Tyrant","1",15,"2007","1","http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/Folder.jpg");

INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("1","4:41","Blood On Your Hands",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("2","4:15","The Last Enemy",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("3","3:22","I Will Live Again",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("4","4:54","In This Shallow Grave",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("5","4:41","Revolution Begins",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("6","4:41","Rise Of The Tyrant",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("7","4:41","The Day You Died",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("8","4:41","Night Falls Fast",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("9","4:41","The Great Darkness",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("10","4:41","Vultures",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("11","4:41","The Oath",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);


INSERT INTO USERS(USER_ID, EMAIL, GOOGLE_ID, USERNAME, PASSWORD, ROLE_ID) VALUES("1","noolic@gmail.com","000","123","123123","2");
INSERT INTO CONSUMERS(CONSUMER_ID,IMG_PATH, USER_ID) VALUES("1","http://localhost:8888/resources/profile.jpg","1");
--
--INSERT INTO PUBLISHERS(PUBLISHER_ID,AUTHOR_ID,USER_ID) VALUES(1,1,3);
--INSERT INTO PUBLISHERS(PUBLISHER_ID,AUTHOR_ID,USER_ID) VALUES(2,2,4);
--INSERT INTO PUBLISHERS(PUBLISHER_ID,AUTHOR_ID,USER_ID) VALUES(3,3,5);
--INSERT INTO PUBLISHERS(PUBLISHER_ID,AUTHOR_ID,USER_ID) VALUES(4,4,6);
--

--INSERT INTO CONSUMERS(CONSUMER_ID,USER_ID) VALUES(2,2);
--
--INSERT INTO PLAYLISTS(PLAYLIST_ID,PLAYLIST_NAME,CONSUMER_ID) VALUES (1,"Cool Playlist",1);
--INSERT INTO PLAYLISTS(PLAYLIST_ID,PLAYLIST_NAME,CONSUMER_ID) VALUES (2,"Awesome",2);
--

--

--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(2,"2017-04-25 17:22:37","4:11","I Am Not Afraid",1,"http://localhost:8080/music/EminemIamnotAfraid.mp3",1,1,4);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(3,"2017-04-25 17:22:37","5:27","Lose Yourself",2,"http://localhost:8080/music/EminemLoseYourself.mp3",1,1,1);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(4,"2017-04-25 17:22:37","6:04","Rap God",3,"http://localhost:8080/music/EminemRapGod.mp3",2,1,1);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(5,"2017-04-25 17:22:37","5:40","Sing For The Moment",6,"http://localhost:8080/music/EminemSingForTheMoment.mp3",2,1,1);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(6,"2017-04-25 17:22:37","5:51","Superman",2,"http://localhost:8080/music/EminemSuperman.mp3",2,1,1);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(7,"2017-04-25 17:22:37","5:22","Under The Influence",7,"http://localhost:8080/music/EminemUnderTheInfluence.mp3",2,1,1);
--
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(8,"2017-04-25 17:22:37","2:58","My Time Is Now",10,"http://localhost:8080/music/JohnCenaMyTimeIsNow.mp3",7,3,4);
--
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(9,"2017-04-25 17:22:37","4:31","Castle Of Glass",1,"http://localhost:8080/music/LinkinParkCastleOfGlass.mp3",5,4,2);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(10,"2017-04-25 17:22:37","2:47","Hit The Floor",3,"http://localhost:8080/music/LinkinParkHitTheFloor.mp3",5,4,1);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(11,"2017-04-25 17:22:37","3:17","In Between",4,"http://localhost:8080/music/LinkinParkInBetween.mp3",5,4,2);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(12,"2017-04-25 17:22:37","5:57","Iridescent",6,"http://localhost:8080/music/LinkinParkIridescent.mp3",6,4,2);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(13,"2017-04-25 17:22:37","4:19","My December",1,"http://localhost:8080/music/LinkinParkMyDecember.mp3",6,4,2);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(14,"2017-04-25 17:22:37","4:11","New Devide",2,"http://localhost:8080/music/LinkinParkNewDevide.mp3",6,4,2);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(15,"2017-04-25 17:22:37","4:20","Rolling In The Deep",5,"http://localhost:8080/music/LinkinParkRollingInTheDeep.mp3",6,4,2);
--
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(16,"2017-04-25 17:22:37","3:10","Bad Blood",5,"http://localhost:8080/music/TaylorSwiftBadBlood.mp3",3,2,3);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(17,"2017-04-25 17:22:37","3:50","Blank Space",3,"http://localhost:8080/music/TaylorSwiftBlankSpace.mp3",3,2,3);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(18,"2017-04-25 17:22:37","4:05","Eyes Open",1,"http://localhost:8080/music/TaylorSwiftEyesOpen.mp3",3,2,4);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(19,"2017-04-25 17:22:37","3:40","I Knew You Were Trouble",2,"http://localhost:8080/music/TaylorSwiftIKnewYouWereTrouble.mp3",4,2,3);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(20,"2017-04-25 17:22:37","3:47","Love Story",3,"http://localhost:8080/music/TaylorSwiftLoveStory.mp3",4,2,3);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(21,"2017-04-25 17:22:37","3:38","Shake It Off",5,"http://localhost:8080/music/TaylorSwiftShakeItOff.mp3",4,2,3);
--INSERT INTO SONGS(SONG_ID,BIRTH_DATE,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES(22,"2017-04-25 17:22:37","3:51","Style",6,"http://localhost:8080/music/TaylorSwiftStyle.mp3",4,2,3);
--
--INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES(5,1);
--INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES(7,1);
--INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES(9,1);
--
--INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES(11,2);
--INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES(13,2);
--INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES(15,2);