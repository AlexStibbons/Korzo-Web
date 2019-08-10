// MongoDB practice file

// shows all databases
show dbs

// creates or uses a database
// if new db, it won't show until it has some tables/collections
use practiceDb

// shows all "tables"
show collections

// to delete a db
// first use it
// then
// db.dropDatabase();

// creates a "table"
db.createCollection('posts')

// now insert documents/data
// db. -> current database
// db.posts -> the collection in which data is interted
db.posts.insert(
    {
        title: 'Post one',
        body: 'post one body one',
        category: 'tutorial',
        likes: 3,
        tags: ['database', 'tuts'],
        user: {
            name: 'hey joe',
            status: 'author'
        },
        date: Date() // inserts current date
    }
)

// inserting many must start with array []
db.posts.insertMany([
    {
        title: 'Post two',
        body: 'post two body two',
        category: 'extras',
        likes: 3,
        tags: ['database'],
        user: {
            name: 'hey jane',
            status: 'author'
        },
        date: Date() // inserts current date
    },
    {
        title: 'Post three',
        body: 'body three'
    },
    {
        title: 'post four',
        body: 'body four'
    }
])

// find all from collection [in this case posts]
db.posts.find() // gets all; appent .pretty() for more readibility

// find by parameter
// similar to where clause
// select * from posts where category = "tutorial"
db.posts.find({ category: 'tutorial' }).pretty()

// how many docs that fit the category?
db.posts.find({ category: 'tutorial' }).count()

// get ontly the first N, in this case 2
db.posts.find().limit(2)

// you can always add more methods
db.posts.find().sort({title:1}).limit(2).pretty()

// sorting result
// in {} is the field by which we sort
// 1 means ascending, -1 means descending
db.posts.find().sort( { title: 1 } ).pretty()

// a for each method
// find() gets us all (or some)
// then forEach() tells us what to do
db.posts.find().forEach(
    function(doc) {
        if (doc.user) {
        print('Blog post: ' + doc.title + ' by ' + doc.user.name)
        } else {
            print('Blog post: ' + doc.title + ' by unknown')
        } 
    }
) 

// find by field contains string ignore case
db.posts.find( { title: { $regex: "our", $options: 'i' } } ).pretty()

// db.posts.update() --> replaces the whole thing

// let's update and keep whatever it's there
db.posts.update( {title : 'post four'},
    {
        $set: {
            category: 'tutorial',
            date: Date(),
            user: {
                name: 'hey jane',
                status: 'author'
            }
        }
    },
    {
        upsert: true // --> if it does not exist, create is
    }
)

// increment a value by, in this case, 2
db.posts.update({title: 'Post one'},
{
    $inc: {likes: 2} // or -2, in which case the value will decrease
}
)
// rename will work similarly, instead of inc, put ;rename; instead of 2 put ;'newname';

// deleting; done by ID, not title
db.posts.remove( { title: 'Post one'} )

db.posts.update( {title : 'post four'},
    {
        $set: {
            comments: [
                {
                    user: 'mary',
                    text: 'comment one' 
                },
                {
                    user: 'mary',
                    text: 'comment two' 
                },
                {
                    user: 'joe',
                    text: 'comment three' 
                }
            ]
        }
    }
)

// find in an array?
db.posts.find( {
    comments: {
        $elemMatch: {
            user: 'joe'
        }
    }
}).pretty()

// create an index in order to search more quickly
db.posts.createIndex( { title: 'text' } )

// search by this new index
db.posts.find(
    {
        $text: {
            $search: "\"Post O\"" // what is this?
        }
    }
).pretty()