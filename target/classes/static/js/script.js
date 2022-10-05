//This element in HTML will be holding recipe cards
const recipesContainer = document.getElementById('recipesContainer')
const form = document.querySelector('form')

var favoriteIds=new Array;


//End Points for Get, Create and Delete Recipes
//Spring Boot: changed port from 4000 to 8080
const getrecipeURL =`http://localhost:8080/api/recipes/all`
const postrecipeURL =`http://localhost:8080/api/recipes/create`
const deleterecipeURL =`http://localhost:8080/api/recipes/delete`
const allfavoritesURL = `http://localhost:8080/api/favorites`
const favoriterecipeURL = `http://localhost:8080/api/favorites/like`
const editrecipeURL =`http://localhost:8080/api/recipes/edit`


//Callback method after receiving response from server
const recipesCallback = ({ data: recipes }) => displayRecipes(recipes)

const recipesCallbackForIds = ({ data: recipes }) => buildFavoritesList(recipes)

//Axios GET call to get all recipes
const getAllRecipes = () => axios.get(getrecipeURL).then(recipesCallback).catch(errCallback)

//Error Callback Method. This is called if the server is returning an error.
const errCallback = err => console.log(err.response.data)

//Axios POST call to create recipe
const createRecipe = (body,id) => axios.post(`${postrecipeURL}/${id}`, body).then(recipesCallback).catch(errCallback)

//Axios DELETE call to delete recipe
const deleteRecipe = id => axios.delete(`${deleterecipeURL}/${id}`).then(recipesCallback).catch(errCallback)

//Axios call to GET logged in user Favorites
const getAllFavorites = userId => axios.get(`${allfavoritesURL}/${userId}`).then(recipesCallback).catch(errCallback)

const getAllFavoriteIds = userId => axios.get(`${allfavoritesURL}/${userId}`).then(recipesCallbackForIds).catch(errCallback)

//Axios POST call to favorite recipe
const favoriteRecipe = (body) => axios.post(`${favoriterecipeURL}`, body).then().catch(errCallback)

const editRecipe = (body) => axios.put(`${editrecipeURL}`, body).then(getAllRecipes).catch(errCallback)

function favorite(recipe_id)
{
    let favoriteBodyObj = {
            userId: userId,
            recipeId: recipe_id
        }
    favoriteRecipe(favoriteBodyObj);
}


//This method is called when the Submit button is clicked for Create Recipe form. This function constructs the POST object and calls createRecipe method.
function submitHandler(e) {
    e.preventDefault()

    let title = document.getElementById('createRecipe_name')
    let ingredients = document.getElementById('createRecipe_ingredients')
    let instructions = document.getElementById('createrecipe_instructions')
    let imageUrl = document.getElementById('createRecipe_imgUrl')
    let recipeCreatedBy = document.getElementById('createRecipe_createdBy')

    let bodyObj = {
        recipeName: title.value,
        recipeIngredients: ingredients.value,
        recipeDescription:instructions.value,
        recipeImageURL: imageUrl.value,
        //recipeCreatedBy: recipeCreatedBy.value
    }
    //postrecipeURL = postrecipeURL + recipeCreatedBy.value;
    createRecipe(bodyObj,recipeCreatedBy.value)

    title.value = ''
    ingredients.value = ''
    instructions.value = ''
    imageUrl.value = ''

    document.getElementById("recipe-form").style.display="none";
    document.getElementById("message").innerHTML="<br/><br/>Recipe added succesfully"

}

//This function is to create recipe cards and iterates through response JSON array received from server
function createRecipeCard(recipe) {
    const recipeCard = document.createElement('div')


    recipeCard.classList.add('flip-card')

    let cardStart=`<div class="flip-card-inner">
                                 <div class="flip-card-front">
                                   <img src=${recipe.recipeImageURL} width="500px" height="500px">
                                 </div>
                                 <div class="flip-card-back">
                                   <br/><p class="card-text" id="recipeNameText_${recipe.id}">Recipe Name: ${recipe.recipeName}</p>
                                   <input type="text" id="recipeNameTextBox_${recipe.id}" style="display:none" value='${recipe.recipeName}' class="text-input" size="40"></input>
                                   `

    let cardContinue = "";
    if(userId === `${recipe.userId}` && window.location.href.indexOf("favorites")==-1)
    {

       cardContinue=`
       <button type="button"  class="btn" id="submitNewRecipeBtn_${recipe.id}" onclick="submitNewRecipeBtn(${recipe.id})" style="display:none">Submit</button>&nbsp;&nbsp;
       <button type="button"  class="btn" id="editRecipeBtn_${recipe.id}" onclick="editRecipeName(${recipe.id},'${recipe.recipeName}')">Edit Recipe Name</button>&nbsp;&nbsp;`
    }



     cardContinue= cardContinue + `<p class="card-text">Ingredients: <br/> ${recipe.recipeIngredients}</p><br/>
                                   <p class="card-text">Instructions: <br/>
                                     ${recipe.recipeDescription}</p><br/>`

   let cardDelete = "";
   if(userId === `${recipe.userId}` && window.location.href.indexOf("favorites")==-1)
   {
     cardDelete=`<button type="button"  class="btn" id="deleteRecipeBtn" onclick=deleteRecipe(${recipe.id})>Delete Recipe</button>&nbsp;&nbsp;`
   }
   if(window.location.href.indexOf("favorites")===-1)
   {
        if(favoriteIds.indexOf(recipe.id)!= -1){
            cardDelete= cardDelete+`<br/><p>Your Favorite</p>`
        }
        else{
            cardDelete= cardDelete+`<button type="button"  class="btn" id="favoriteRecipeBtn" onclick=favorite(${recipe.id})>Favorite Recipe</button>&nbsp;&nbsp;`
        }
    }
    let cardEnd=`</div></div></div>`

    recipeCard.innerHTML =  cardStart+cardContinue+cardDelete+cardEnd;

    recipesContainer.appendChild(recipeCard)
}

//This is callback function that is called after receiving data from server
function displayRecipes(arr) {
    recipesContainer.innerHTML = ''
    for (let i = 0; i < arr.length; i++) {
        createRecipeCard(arr[i])
    }
}

function buildFavoritesList(recipesList){
//console.log("I am supposed to be #2")
for (let i = 0; i < recipesList.length; i++) {
    favoriteIds.push(recipesList[i].id)
    }
    //console.log("favoriteIds -- "+favoriteIds);

    getAllRecipes();
}

//This method is to show Create Recipe form
function showRecipeForm()
{
    document.getElementById("recipe-form").style.display="block"
    document.getElementById("createRecipe_createdBy").value=userId;
}



//Search for "easy way to get cookie by name" and got this sample code from Stack Overflow
const getCookieValue = (name) => (
  document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)')?.pop() || ''
)

const userId= getCookieValue("userId");

function logout()
{
    document.cookie.split(";").forEach(function(c) { document.cookie = c.replace(/^ +/, "").replace(/=.*/, "=;expires=" + new Date().toUTCString() + ";path=/"); });
    window.location="http://localhost:8080"

}

function editRecipeName(recipeId, recipeName)
{
    document.getElementById("recipeNameText_"+recipeId).style.display="none";
    document.getElementById("editRecipeBtn_"+recipeId).style.display="none";
    document.getElementById("recipeNameTextBox_"+recipeId).style.display="block";
    document.getElementById("submitNewRecipeBtn_"+recipeId).style.display="block";


 console.log("Recipe Id: " + recipeId + "::: Recipe Name: " + recipeName);
}

function submitNewRecipeBtn(recipeId)
{
    let bodyObj = {
            id: recipeId,
            recipeName: document.getElementById("recipeNameTextBox_"+recipeId).value
    }
    editRecipe(bodyObj);
    //console.log("submitNewRecipeBtn - Recipe Id: " + recipeId );
}