--#import.sql file
INSERT INTO GENRES(GENRE_ID,GENRE_NAME)VALUES("1","Death Metal");

INSERT INTO ROLES(ROLE_ID, NAME) VALUES("1", "PUBLISHER");
INSERT INTO ROLES(ROLE_ID, NAME) VALUES("2", "CONSUMER");

INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("1","Arch Enemy",152,"1","http://localhost:8888/resources/ArchEnemy/Band.jpg");
INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("2","Sabaton",123,"1","http://localhost:8888/resources/Sabaton/Band.jpg");
INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("3","Powerwolf",156,"1","http://localhost:8888/resources/Powerwolf/Band.png");
INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("4","Manowar",189,"1","http://localhost:8888/resources/Manowar/Band.jpg");
INSERT INTO AUTHORS (AUTHOR_ID, NAME, RATING, GENRE_ID, IMG_PATH) VALUES ("5","Unleash The Archers",112,"1","http://localhost:8888/resources/UnleashTheArchers/Band.jpg");

INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("1","Root of All Evil","1",15,"2011","1","http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("2","War Eternal","1",12,"2014","1","http://localhost:8888/resources/ArchEnemy/WarEternal/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("3","Will To Power","1",20,"2017","1","http://localhost:8888/resources/ArchEnemy/WillToPower/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("4","Rise Of The Tyrant","1",13,"2007","1","http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("5","The Lord Of Steel","4",17,"2012","1","http://localhost:8888/resources/Manowar/TheLordOfSteel/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("6","Warriors Of The World","4",20,"2002","1","http://localhost:8888/resources/Manowar/WarriorsOfTheWorld/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("7","Kings Of Metal","4",10,"1988","1","http://localhost:8888/resources/Manowar/KingsOfMetal/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("8","Blessed & Possessed","3",14,"2015","1","http://localhost:8888/resources/Powerwolf/Blessed&Possessed/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("9","Blood Of The Saints","3",12,"2011","1","http://localhost:8888/resources/Powerwolf/BloodOfTheSaints/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("10","Heroes","3",18,"2014","1","http://localhost:8888/resources/Sabaton/Heroes/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("11","Coat Of Arms","3",13,"2010","1","http://localhost:8888/resources/Sabaton/CoatOfArms/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("12","Carolus Rex","3",15,"2012","1","http://localhost:8888/resources/Sabaton/CarolusRex/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("13","Apex","5",16,"2017","1","http://localhost:8888/resources/UnleashTheArchers/Apex/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("14","Time Stands Still","5",12,"2015","1","http://localhost:8888/resources/UnleashTheArchers/TimeStandsStill/Folder.jpg");
INSERT INTO ALBUMS (ALBUM_ID, NAME, AUTHOR_ID, RATING, RECORD_DATE, GENRE_ID, IMG_PATH)VALUES("15","Behold The Devastation","5",17,"2008","1","http://localhost:8888/resources/UnleashTheArchers/BeholdTheDevastation/Folder.jpg");


INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("1","4:41","Blood On Your Hands",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("2","4:15","The Last Enemy",7,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("3","3:22","I Will Live Again",8,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("4","4:54","In This Shallow Grave",9,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("5","4:11","Revolution Begins",10,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("6","4:33","Rise Of The Tyrant",9,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("7","4:52","The Day You Died",8,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("8","3:18","Night Falls Fast",7,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("9","4:46","The Great Darkness",3,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("10","6:34","Vultures",6,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("11","4:16","The Oath",5,"http://localhost:8888/resources/ArchEnemy/RiseOfTheTyrant/songname123.mp3",4,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("12","1:06","The Root Of All Evil",12,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("13","3:46","Beast Of Man",3,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("14","3:47","The Immortal",6,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("15","3:48","Diva Satanica",11,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("16","5:23","Demonic Science",6,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("17","4:25","Bury Me An Engel",6,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("18","4:24","Dead Inside",13,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("19","3:25","Dark Insanity",8,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("20","4:50","Pilgrim",4,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("21","1:40","Demoniality",2,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("22","3:33","Transmigration Macabre",8,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("23","4:22","Silverwing",8,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("24","7:53","Bridge Of Destiny",7,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("25","3:17","Wings Of Tomorrow",5,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("26","3:07","Walk In The Shadows",8,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("27","2:44","Warning",9,"http://localhost:8888/resources/ArchEnemy/RootOfAllEvil/songname123.mp3",1,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("28","1:12","Tempore Nihil Sanat ",6,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("29","3:43","Never Forgive, Never Forget",7,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("30","4:16","War Eternal",8,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("31","4:01","As The Pages Burn",9,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("32","4:05","No More Regrets",10,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("33","4:37","You Will Know My Name",9,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("34","1:10","Graveyard of Dreams",8,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("35","2:59","Stolen Dreams",7,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("36","5:23","Time is Black",3,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("37","4:05","On And On",6,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("38","4:38","Avalanche",5,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("39","3:47","Down To Nothing",12,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("40","3:28","Not Long To This World",3,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("41","3:03","Shadow On The Wall",6,"http://localhost:8888/resources/ArchEnemy/WarEternal/songname123.mp3",2,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("42","3:15","The Race",6,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("43","3:55","Blood In The Water",7,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("44","4:53","The World Is Yours",8,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("45","5:15","The Eagle Flies Alone",14,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("46","4:47","Reason To Believe",10,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("47","3:50","Murder Scene",9,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("48","4:48","First Day In Hell",8,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("49","1:09","Saturnine",7,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("50","6:40","Dreams of Retribution",3,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("51","4:05","My Shadows And I",6,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("52","6:35","A Fight I Must Win",5,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("53","1:19","Set Flame To The Night",12,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("54","3:21","Back To Back",3,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("55","2:46","City Baby Attacked By Rats",6,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",3,1,1);

INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("56","7:17","Awakening",9,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("57","3:56","Shadow Guide",13,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("58","4:02","Matriarch",10,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("59","5:54","Cleanse The Bloodlines",9,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("60","5:05","The Coward's Way",5,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("61","8:05","False Walls",6,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("62","5:37","Ten Thousands Against One",11,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("63","6:35","Earth And Ashes",8,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("64","5:46","Call Me Immortal",10,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("65","8:23","Apex",10,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("66","4:16","Queen Of The Reich",9,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",13,5,1);

INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("67","1:47","Northern Passage",9,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("68","5:39","Frozen Steel",13,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("69","5:31","Hail of the Tide",10,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("70","6:25","Tonight We Ride",9,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("71","3:13","Test Your Metal",5,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("72","5:21","Crypt",6,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("73","6:53","No More Heroes",11,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("74","9:16","Dreamcrusher",8,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("75","5:28","Going Down Fighting",10,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);
INSERT INTO SONGS(SONG_ID,SONG_DURATION,NAME,RATING,SONG_URL,ALBUM_ID,AUTHOR_ID,GENRE_ID)VALUES("76","6:00","Time Stands Still",10,"http://localhost:8888/resources/ArchEnemy/WillToPower/songname123.mp3",14,5,1);



INSERT INTO USERS(USER_ID, EMAIL, GOOGLE_ID, USERNAME, PASSWORD, ROLE_ID) VALUES("1","noolic@gmail.com","000","SomeGuy","123123","2");
INSERT INTO USERS(USER_ID, EMAIL, GOOGLE_ID, USERNAME, PASSWORD, ROLE_ID) VALUES("2","archenemy@gmail.com","001","ArchEnemy","123123","1");
INSERT INTO CONSUMERS(CONSUMER_ID,IMG_PATH, USER_ID) VALUES("1","http://localhost:8888/resources/profile2.jpeg","1");

INSERT INTO PUBLISHERS(PUBLISHER_ID,AUTHOR_ID,USER_ID) VALUES("1","1","2");


INSERT INTO PLAYLISTS(PLAYLIST_ID,PLAYLIST_NAME,RATING,CONSUMER_ID) VALUES ("1","Favorites",0,"1");
INSERT INTO PLAYLISTS(PLAYLIST_ID,PLAYLIST_NAME,RATING,CONSUMER_ID) VALUES ("2","Cool Playlist",5,"1");
INSERT INTO PLAYLISTS(PLAYLIST_ID,PLAYLIST_NAME,RATING,CONSUMER_ID) VALUES ("3","Awesome",3,"1");

INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("75","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("67","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("59","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("61","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("63","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("71","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("45","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("41","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("72","1");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("12","2");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("5","2");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("6","2");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("27","3");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("21","3");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("15","3");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("16","3");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("9","3");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("3","3");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("65","3");
INSERT INTO SONG_PLAYLISTS(SONG_ID,PLAYLIST_ID)VALUES("66","3");

