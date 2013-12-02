USE [master]
GO
/****** Object:  Database [H2store]    Script Date: 12/02/2013 22:23:00 ******/
CREATE DATABASE [H2store] ON  PRIMARY 
( NAME = N'H2store', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS2008\MSSQL\DATA\H2store.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'H2store_log', FILENAME = N'c:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS2008\MSSQL\DATA\H2store_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [H2store] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [H2store].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [H2store] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [H2store] SET ANSI_NULLS OFF
GO
ALTER DATABASE [H2store] SET ANSI_PADDING OFF
GO
ALTER DATABASE [H2store] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [H2store] SET ARITHABORT OFF
GO
ALTER DATABASE [H2store] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [H2store] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [H2store] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [H2store] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [H2store] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [H2store] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [H2store] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [H2store] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [H2store] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [H2store] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [H2store] SET  DISABLE_BROKER
GO
ALTER DATABASE [H2store] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [H2store] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [H2store] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [H2store] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [H2store] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [H2store] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [H2store] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [H2store] SET  READ_WRITE
GO
ALTER DATABASE [H2store] SET RECOVERY SIMPLE
GO
ALTER DATABASE [H2store] SET  MULTI_USER
GO
ALTER DATABASE [H2store] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [H2store] SET DB_CHAINING OFF
GO
USE [H2store]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 12/02/2013 22:23:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductId] [nvarchar](20) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[CategoryId] [int] NULL,
	[Price] [float] NULL,
	[Description] [ntext] NULL,
	[Image_Link] [nchar](50) NULL,
	[New_Product] [bit] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 12/02/2013 22:23:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderID] [int] NOT NULL,
	[ProductID] [nvarchar](20) NULL,
	[ProductName] [nvarchar](50) NULL,
	[Quantity] [int] NULL,
	[Price] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 12/02/2013 22:23:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerEmail] [nvarchar](50) NULL,
	[TotalPrice] [float] NULL,
	[OrderDate] [nvarchar](50) NULL,
	[ReceiveDate] [nvarchar](50) NULL,
	[Note] [nvarchar](max) NULL,
	[Status] [nvarchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 12/02/2013 22:23:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[Account] [nvarchar](50) NOT NULL,
	[Password] [nchar](50) NOT NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[Account] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 12/02/2013 22:23:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerName] [nvarchar](50) NOT NULL,
	[Gender] [nvarchar](10) NULL,
	[Phone] [nvarchar](50) NOT NULL,
	[Address] [nvarchar](50) NOT NULL,
	[CustomerEmail] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Question] [nvarchar](max) NOT NULL,
	[Answer] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 12/02/2013 22:23:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryId] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
