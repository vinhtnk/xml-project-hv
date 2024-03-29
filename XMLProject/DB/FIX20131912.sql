USE [master]
GO
/****** Object:  Database [H2store]    Script Date: 12/19/2013 15:02:01 ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 12/19/2013 15:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [nvarchar](50) NOT NULL,
	[Gender] [nvarchar](10) NULL,
	[Phone] [nvarchar](50) NULL,
	[Address] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Role] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Users] ON
INSERT [dbo].[Users] ([UserID], [UserName], [Gender], [Phone], [Address], [Email], [Password], [Role]) VALUES (1, N'Hoang', N'Nam', N'0123456789', N'123 cong hoa', N'hoang186@gmail.com', N'123456', N'Admin')
INSERT [dbo].[Users] ([UserID], [UserName], [Gender], [Phone], [Address], [Email], [Password], [Role]) VALUES (2, N'HHD', N'Nam', N'0345678902', N'23 Le Duan', N'hoang@yahoo.com', N'123456', N'Customer')
INSERT [dbo].[Users] ([UserID], [UserName], [Gender], [Phone], [Address], [Email], [Password], [Role]) VALUES (3, N'fgsda', N'Nữ', N'523409', N'123 cộng hòa', N'hoang123@yahoo.com', N'12345', N'Customer')
SET IDENTITY_INSERT [dbo].[Users] OFF
/****** Object:  Table [dbo].[Product]    Script Date: 12/19/2013 15:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[ProductId] [nvarchar](20) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[CategoryId] [int] NULL,
	[Price] [int] NULL,
	[Descriptions] [ntext] NULL,
	[Image_Link] [nchar](50) NULL,
	[New_Product] [bit] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'HTC8X', N'HTC 8X', 4, 9490000, N'Màn hình: HD, 4.3 inches. Hệ điều hành: Windows Phone 8. CPU: Dual-core 1.5 GHz. Camera: 8.0 MP. Dung lượng pin: 1800 mAh', N'htc_8x.jpg                                        ', NULL)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'HTCOne', N'HTC One ', 4, 11999000, N'Màn hình: HD, 4.7 inches. Hệ điều hành: Android 4.0 (ICS). CPU: CPU lõi tứ 1,7Ghz. Camera: 4.0 MP. Dung lượng pin: 1800 mAh', N'htcone.jpg                                        ', 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'iP5', N'iPhone 5 16 GB', 1, 14999000, N'Màn hình DVGA, 4.0 inches. Hệ điều hành: iOS 6. CPU: Dual-core 1.2 GHz. Camera: 8.0 MP. Dung lượng pin: 1440 mAh', N'iphone5.jpg                                       ', 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'ip5s', N'iPhone 5s 16GB', 1, 16990000, N'Màn hình DVGA, 4.0 inches. Hệ điều hành: iOS 7. CPU: Apple A7. Camera: 8.0 MP. Dung lượng pin: 1440 mAh', N'iphone5s.jpg                                      ', 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'LGOG', N'LG Optimus G', 6, 8299000, N'Màn hình: HD, 4.7 inches. Hệ điều hành: Android 4.1.2(Jelly Bean).  CPU: Quad-core 1.5 GHz.  Camera: 13 MP. Dung lượng pin: 2100 mAh', N'LG_Optimus_G.jpg                                  ', 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'LGOL7', N'LG Optimus L7', 6, 4999000, N'Màn hình HVGA, 4.3 inches. Hệ điều hành: Android 4.0 (ICS). CPU: Solo-core 1 GHz. Camera: 5.0 MP. Dung lượng pin: 1700 mAh ', N'Lg_optimus_l7_p705.jpg                            ', NULL)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'LGOL9', N'LG Optimus L9 ', 6, 7000000, N'Màn hình qHD, 4.7 inches. Hệ điều hành: Android 4.0.4 (ICS). CPU: Dual-core 1 GHz. Camera: 8.0 MP. Dung lượng pin: 2150 mAh', N'LG_Optimus_L9_P768.jpg                            ', NULL)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'NL820', N'Nokia Lumia 820', 3, 8490000, N'Màn hình WVGA, 4.3 inches. Hệ điều hành: Windows Phone 8. CPU: Dual-core 1.5GHz. Camera: 8.0 MP. Dung lượng pin: 1650mAh', N'Nokia_Lumia_820.jpg                               ', NULL)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'NL920', N'Nokia Lumia 920', 3, 11490000, N'Màn hình: HD, 4.5 inches. Hệ điều hành: Windows Phone 8. CPU: Dual-core 1.5 GHz. Camera: 8.0 MP. Dung lượng pin: 2000 mAh', N'nokia_lumia_920.jpg                               ', 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'SLXZ', N'Sony Xperia Z', 5, 11000000, N'Màn hình Full HD, 5.0", 1080 x 1920 pixels. Hệ điều hành:  Android 4.1. CPU: Quad-core 1.5 GHz Krait, RAM: 2 GB.Dung lượng pin: 2330 mAh', N'sony_xperia_z.jpg                                 ', 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'SNXP', N'Sony Xperia P', 5, 5990000, N'Màn hình qHD, 4.0 inches. Hệ điều hành: Android 2.3 (Gingerbread). CPU: Dual-core 1 GHz. Camera: 8.0 MP. Dung lượng pin: 1305 mAh', N'sony_xperia_p.jpg                                 ', NULL)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'SNXSL', N'Sony Xperia SL', 5, 7200000, N'Màn hình HD, 4.3 inches. Hệ điều hành:Android 4.0.4 (ICS). CPU: Dual-core 1.7 GHz. Camera: 12 MP. Dung lượng pin: 1750 mAh', N'sony_xperia_sl.jpg                                ', NULL)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'SSGLXNII', N'Samsung Galaxy Note II', 2, 11500000, N'Màn hình HD, 5.5 inches. Hệ điều hành: Android 4.1 (Jelly Bean). CPU: Quad-core 1.6 GHz. Camera: 8.0 MP. Dung lượng pin: 3100 mAh', N'ss_galaxy_note2.jpg                               ', 1)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'SSGLXS3', N'Samsung Galaxy S3', 2, 8000000, N'Màn hình HD, 4.8 inches. Hệ điều hành: Android 4.0.4 (ICS). CPU: Quad-core 1.4 GHz. Camera: 8.0 MP. Dung lượng pin: 2100 mAh', N'ss_galaxy_s3.jpg                                  ', NULL)
INSERT [dbo].[Product] ([ProductId], [ProductName], [CategoryId], [Price], [Descriptions], [Image_Link], [New_Product]) VALUES (N'SSGLXY', N'Samsung Galaxy Y', 2, 1990000, N'Màn hình QVGA, 3.0 inches. Hệ điều hành: Android 2.3.6. CPU: Solo-core 830 MHz. Camera: 2.0 MP. Dung lượng pin 1200 mAh', N'ss_galaxy_y.jpg                                   ', NULL)
/****** Object:  Table [dbo].[Orders]    Script Date: 12/19/2013 15:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](50) NULL,
	[TotalPrice] [int] NULL,
	[OrderDate] [date] NULL,
	[ReceiveDate] [date] NULL,
	[Note] [nvarchar](max) NULL,
	[Status] [nvarchar](50) NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Orders] ON
INSERT [dbo].[Orders] ([OrderID], [Email], [TotalPrice], [OrderDate], [ReceiveDate], [Note], [Status]) VALUES (75, N'hoang186@gmail.com', 23298000, CAST(0xF3370B00 AS Date), NULL, NULL, NULL)
INSERT [dbo].[Orders] ([OrderID], [Email], [TotalPrice], [OrderDate], [ReceiveDate], [Note], [Status]) VALUES (76, N'hoang@yahoo.com', 31989000, CAST(0xF4370B00 AS Date), NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Orders] OFF
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 12/19/2013 15:02:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderID] [int] NOT NULL,
	[ProductID] [nvarchar](20) NULL,
	[ProductName] [nvarchar](50) NULL,
	[Quantity] [int] NULL,
	[Price] [int] NULL
) ON [PRIMARY]
GO
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [ProductName], [Quantity], [Price]) VALUES (75, N'iP5', N'iPhone 5 16 GB', 1, 14999000)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [ProductName], [Quantity], [Price]) VALUES (75, N'LGOG', N'LG Optimus G', 1, 8299000)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [ProductName], [Quantity], [Price]) VALUES (76, N'iP5', N'iPhone 5 16 GB', 1, 14999000)
INSERT [dbo].[OrderDetail] ([OrderID], [ProductID], [ProductName], [Quantity], [Price]) VALUES (76, N'ip5s', N'iPhone 5s 16GB', 1, 16990000)
/****** Object:  Table [dbo].[Category]    Script Date: 12/19/2013 15:02:01 ******/
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
SET IDENTITY_INSERT [dbo].[Category] ON
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (1, N'Apple                                             ')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (2, N'Samsung                                           ')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (3, N'Nokia                                             ')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (4, N'HTC                                               ')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (5, N'Sony                                              ')
INSERT [dbo].[Category] ([CategoryId], [CategoryName]) VALUES (6, N'LG                                                ')
SET IDENTITY_INSERT [dbo].[Category] OFF
