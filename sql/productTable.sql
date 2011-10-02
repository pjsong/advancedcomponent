CREATE TABLE `test`.`product` (
  `ProductID` VARCHAR(45) NOT NULL,
  `Title` VARCHAR(100),
  `LongDesc` TEXT,
  `ShortDesc` VARCHAR(150),
  `ImgUrl` VARCHAR(150),
  `CatID` VARCHAR(10),
  PRIMARY KEY (`ProductID`)
);
CREATE TABLE `test`.`category` (
  `CatID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name0` VARCHAR(45),
  `Desc0` TEXT,
  `Name1` VARCHAR(45),
  `Desc1` TEXT,
  `Name2` VARCHAR(45),
  `Desc2` TEXT,
  PRIMARY KEY (`CatID`)
);
CREATE TABLE `test`.`pickattributes` (
  `ID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`)
);
CREATE TABLE `test`.`freeattributes` (
  `ID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `ProductID` VARCHAR(45) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`)
);
CREATE TABLE `test`.`Customer` (
  `ID` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `PhoneNumber` VARCHAR(45) NOT NULL,
  `QQ` VARCHAR(45) NOT NULL,
  `MSN` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`)
);