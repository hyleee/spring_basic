-- DROP DATABASE IF EXISTS ssafydb;

CREATE DATABASE IF NOT EXISTS ssafydb;


USE ssafydb;

CREATE TABLE IF NOT EXISTS users (
    userId VARCHAR(20) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews (
    reviewId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    videoId INT NOT NULL,
    content VARCHAR(200) NOT NULL,
    userId VARCHAR(20) NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(userId) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS videos (
    videoId INT PRIMARY KEY NOT NULL,
    title VARCHAR(100) NOT NULL,
    fitPartName VARCHAR(100) NOT NULL,
    youtubeId VARCHAR(100) NOT NULL,
    channelName VARCHAR(100) NOT NULL,
    viewCnt INT NOT NULL
);

-- drop table follows;
CREATE TABLE IF NOT EXISTS follows (
    followId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    followingId VARCHAR(20) NOT NULL,
    followerId VARCHAR(20) NOT NULL,
    FOREIGN KEY (followingId) REFERENCES users(userId),
    FOREIGN KEY (followerId) REFERENCES users(userId)
);

CREATE TABLE IF NOT EXISTS favorites (
    favoriteId INT PRIMARY KEY NOT NULL,
    videoId INT NOT NULL,
    userId VARCHAR(20) NOT NULL,
    FOREIGN KEY (videoId) REFERENCES videos(videoId) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users(userId) ON DELETE CASCADE
);

SHOW CREATE TABLE follows;

SELECT * FROM users;

SELECT * FROM reviews;

SELECT * FROM videos;

SELECT * FROM `follows`;