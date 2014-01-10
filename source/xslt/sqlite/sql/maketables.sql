
-- al available titles for a person
create table PersonTitle
(
	Title varchar(10) primary key
);

insert into PersonTitle
       (Title)
values
	('Mr.'),
	('Ms.'),
	('Mrs.'),
	('Dr.');

create table PersonSuffix
(
	Suffix varchar(10) primary key
);

-- all suffixes after their name
insert into PersonSuffix
       (Suffix)
values
	('Sr.'),
	('Jr.'),
	('II'),
	('III'),
	('IV'),
	('V'),
	('VI'),
	('VII'),
	('VIII'),
	('IX'),
	('X'),
	('PhD.');

-- the main person table
create table if not exists Person
(
	FirstName varchar(50) NOT NULL,
	MiddleName varchar(50),
	LastName varchar(50) NOT NULL,
	Title varchar(10),
	Suffix varchar(10),
	Born Date,
	Died Date,
	ID varchar(100) primary key,
	constraint FK_Person_Title foreign key (Title) references PersonTitle(Title)
		   on delete set null
		   on update cascade,
	constraint FK_Person_Suffix foreign key (Suffix) references PersonSuffix(Suffix)
		   on delete set null
		   on update cascade
);

-- the root of the project, a song index number
create table if not exists SongIndex
(
	ID varchar(100) primary key
);

-- some songIndexes have a see also 
-- this is a reflexive property between two index entries
create table if not exists SongSeeAlso
(
	LowerIndex varchar(100) not null,
	UpperIndex varchar(100) not null,
	constraint PK_SongSeeAlso primary key (LowerIndex,UpperIndex)
		   on conflict replace,
	constraint FK_SongSeeAlso_LowerIndex foreign key (LowerIndex)
		   references SongIndex(ID),
	constraint FK_SongSeeAlso_UpperIndex foreign key (UpperIndex)
		   references SongIndex(ID)
);

-- make sure all new see alsos added are unique
create trigger if not exists insert_SongSeeAlso
       after insert on SongSeeAlso
       for each row when new.LowerIndex > new.UpperIndex
begin
	update SongSeeAlso
	set
		LowerIndex = new.UpperIndex,
		UpperIndex = new.LowerIndex
	where
		LowerIndex = new.LowerIndex
		and UpperIndex = new.UpperIndex;
		
end;

-- some songIndexes are crossreference to other 
-- this is a reflexive property between two index entries
create table if not exists SongCrossRef
(
	LowerIndex varchar(100) not null,
	UpperIndex varchar(100) not null,
	constraint PK_SongCrossRef primary key (LowerIndex,UpperIndex)
		   on conflict ignore,
	constraint FK_SongCrossRef_LowerIndex foreign key (LowerIndex)
		   references SongIndex(ID),
	constraint FK_SongCrossRef_UpperIndex foreign key (UpperIndex)
		   references SongIndex(ID)
);

--  make sure all new cross references are unique
create trigger if not exists insert_SongCrossRef
       after insert on SongCrossRef
       for each row when new.LowerIndex > new.UpperIndex
begin
	update SongCrossRef
	set
		LowerIndex = new.UpperIndex,
		UpperIndex = new.LowerIndex
	where
		LowerIndex = new.LowerIndex
		and UpperIndex = new.UpperIndex;
		
end;

-- valid types of media that a collection can be
create table if not exists MediaType
(
	MediaType char(1) primary key
);

insert into MediaType
       (MediaType)
values
	('b'), -- book/paper
	('v'), -- video
	('r'); -- sound recording

-- a collection of songs, usually a book or recording
create table if not exists SongCollection
(
	Title varchar(500) not null,
	MediaType char(1) not null,
	Publisher varchar(100),
	PublisherLocation varchar(100),
	Published Date,
	Edition int,
	CollectionLength int,
	URL varchar(500),
	ID varchar(100) primary key,
	constraint FK_SongCollection_MediaType foreign key (MediaType)
		   references MediaType(MediaType)
		   on update cascade
		   on delete set null
);

-- all the listed authors of a book
create table if not exists SongCollectionAuthor
(
	AuthorID varchar(100) not null references Person(ID),
	CollectionID varchar(100) not null references SongCollection(ID),
	constraint PK_SongCollectionAuthor primary key (AuthorID, CollectionID)
);

-- all the editors of a book
create table if not exists SongCollectionEditor
(
	EditorID varchar(100) not null references Person(ID),
	CollectionID varchar(100) not null references SongCollection(ID),
	constraint PK_SongCollectionEditor primary key (EditorID, CollectionID)
);

-- TODO make song table(s)