
let selectedFriend = null;

/**
 * 
 * @param {*} element is the html div "phoneListContainer"
 * getFriends fetches all friends in phonebook. json format 
 * and adds the result to html document
 */
function getFriends(element) {
    fetch("http://localhost:8080/phonebook")
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            const friendList = createPhoneList(data);
            element.appendChild(friendList);

            // Creating a global variable of the friends list using "window". This is used in the search function
            window.friends = data;
            console.log("PhoneBook fetched");
        })
        .catch(error => {
            console.error("Error fetching the phone book:", error);
        });
}
/**
 * 
 * @param {*} friends json list of all friends fetched from the API
 * @returns a phonelist div to add to the phoneListContainer
 */
function createPhoneList(friends) {

    //Creating the phonelist by giving the method the JSON data.

    //creating a div element for all numbers.
    const phoneList = document.createElement("div");
    phoneList.id = "phoneList";
    //for every post in data JSON list
    for (let i = 0; i < friends.length; i++) {

        const friend = friends[i];
        //create a header
        const friendName = createfriendName(friend);

        const phoneNumber = createPhoneNumber(friend);
        //create a separate div for every friend and append header and image.
        const phoneListDiv = document.createElement("div");
        //Assigning a class name. See CSS for separation of Name and Number
        phoneListDiv.className = "friendEntry";

        // this creates a tag on the html element so you can find it by friend name
        phoneListDiv.dataset.friendName = friend.name;


        phoneListDiv.appendChild(friendName);
        phoneListDiv.appendChild(phoneNumber);



        // Add click event listener
        phoneListDiv.addEventListener("click", () => {
            selectFriendHandler(friend);
        });

        //append small div do main div
        phoneList.appendChild(phoneListDiv);
    }
    // return the main div
    return phoneList;

}


/**
 * 
 * @param {*} friend .json friend
 * @returns header html
 */
function createfriendName(friend) {
    const header = document.createElement("h5");
    header.textContent = friend.name; // Assuming `friend` has a `name` property
    return header;
}

/**
 * 
 * @param {*} friend .json friend
 * @returns phonenumber html
 */
function createPhoneNumber(friend) {
    const phonenumber = document.createElement("h5");
    phonenumber.textContent = friend.phoneNumber;
    return phonenumber;
}


function deleteFriend(name) {
    fetch(`http://localhost:8080/phonebook/${encodeURIComponent(name)}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            // Optionally, handle the UI update after successful deletion
            console.log(`Friend with name ${name} deleted successfully.`);
            // You might want to refresh the list or remove the entry from the UI
            refreshPhoneBook(); // Call this function to update the list after deletion
        })
        .catch(error => {
            console.error('Error deleting the friend:', error);
        });
}

function addFriend(name, phoneNumber) {

    fetch(`http://localhost:8080/phonebook/${encodeURIComponent(name)}/${encodeURIComponent(phoneNumber)}`, {
        method: 'POST'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network error when trying to add friend');
            }
            console.log(`Friend with name ${name} added successfully.`);
            refreshPhoneBook();
        })
        .catch(error => {
            console.error('Error adding the friend:', error);
        });


}

/**
 * Makes friend to be currently selected. 
 * @param {*} friend.name 
 */
function selectFriendHandler(friend) {

    const previouslySelected = document.querySelector(".friendEntrySelected");


    // If the clicked friend is already selected, unselect it
    if (previouslySelected && previouslySelected.dataset.friendName === friend.name) {
        previouslySelected.classList.remove("friendEntrySelected");
        previouslySelected.classList.add("friendEntry");
        selectedFriend = null; // No friend is selected now
        console.log("Friend unselected: " + friend.name);
        return;
    }
    // If a different friend is selected, deselect the previously selected friend

    if (previouslySelected) {
        previouslySelected.classList.remove("friendEntrySelected");
        previouslySelected.classList.add("friendEntry");
        console.log("Friend unselected: " + friend.name);

    }


    selectedFriend = friend.name;
    console.log("Currently selected friend: " + friend.name);

    //this looks for the html friend name given in the createPhonelist method
    const findCurrentFriend = document.querySelector(`[data-friend-name="${friend.name}"]`);

    if (findCurrentFriend) {
        findCurrentFriend.classList.remove("friendEntry");
        findCurrentFriend.classList.add("friendEntrySelected");
    }


}




function refreshPhoneBook() {
    const phoneBookContainer2 = document.querySelector("#phoneBookContainer2");
    phoneBookContainer2.innerHTML = "";
    getFriends(phoneBookContainer2);



}



// searching the friend list with text. 
function searchfriends(text, friends) {
    const filteredfriends = friends.filter(friend => {
        const fullName = `${friend.name}`.toLowerCase();
        return fullName.includes(text.toLowerCase());
    });

    // Clear the current friend list
    const phoneBookContainer2 = document.querySelector("#phoneBookContainer2");
    phoneBookContainer2.innerHTML = "";

    const currentList = document.getElementById("friendList");
    if (currentList) {
        phoneBookContainer2.removeChild(currentList);
    }

    // Display the filtered list
    const newfriendList = createPhoneList(filteredfriends);
    phoneBookContainer2.appendChild(newfriendList);
}






// Gather event listeners after the document is loaded
document.addEventListener("DOMContentLoaded", () => {
    const phoneBookContainer2 = document.querySelector("#phoneBookContainer2");
    //Calling getfriends to display the phonelist
    getFriends(phoneBookContainer2);


    const deleteBtn = document.querySelector("#deleteButton");
    deleteBtn.onclick = () => {
        if (selectedFriend != null) {
            deleteFriend(selectedFriend);
        }

        else {
            console.log("No friend seems to be selected");
        }
    };

    // pop up form when clicking add
    const modal = document.querySelector("#addFriendModal");

    window.onclick = (event) => {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };


    // the add button shows the "modal" form
    const addBtn = document.querySelector("#addButton");
    addBtn.onclick = () => {
        modal.style.display = "block";

    }
    // hides the add form(the x icon in the modal)
    const closeBtn = document.querySelector(".close");
    closeBtn.onclick = () => {
        modal.style.display = "none";
    }

    // Handle form submission (you can customize this part)
    const addFriendForm = document.getElementById("addFriendForm");

    addFriendForm.onsubmit = (event) => {
        event.preventDefault();
        // Here you can add logic to handle the form submission, e.g., save the data
        const name = document.getElementById("name").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        console.log("Adding friend:", name, phoneNumber);
        addFriend(name, phoneNumber);

        addFriendForm.reset();
        // Close the modal after submission
        modal.style.display = "none";
    };

    const searchBtn = document.getElementById("searchButton");
    searchBtn.onclick = () => {
        const searchField = document.getElementById("searchText");
        const searchText = searchField.value;
        searchfriends(searchText, window.friends);
        // searchField.value="";
    }



});

