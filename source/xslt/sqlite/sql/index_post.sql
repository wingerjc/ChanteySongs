

-- Make sure that all indexes
-- referenced by see also and cross referenced also exist.
insert into SongIndex
select LowerIndex
from SongSeeAlso
except
select ID
from SongIndex;

insert into SongIndex
select UpperIndex
from SongSeeAlso
except
select ID
from SongIndex;

insert into SongIndex
select LowerIndex
from SongCrossRef
except
select ID
from SongIndex;

insert into SongIndex
select LowerIndex
from SongCrossRef
except
select ID
from SongIndex;

PRAGMA foreign_keys = ON;
