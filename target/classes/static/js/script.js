//This element in HTML will be holding recipe cards
const recipesContainer = document.getElementById('recipesContainer')
const form = document.querySelector('form')


//End Points for Get, Create and Delete Recipes
//Spring Boot: changed port from 4000 to 8080
const getrecipeURL =`http://localhost:8080/api/getallrecipes`
const postrecipeURL =`http://localhost:8080/api/createrecipe`
const deleterecipeURL =`http://localhost:4000/api/deleterecipe`

//Callback method after receiving response from server
const recipesCallback = ({ data: recipes }) => displayRecipes(recipes)

//Axios GET call to get all recipes
const getAllRecipes = () => axios.get(getrecipeURL).then(recipesCallback).catch(errCallback)

//Error Callback Method. This is called if the server is returning an error.
const errCallback = err => console.log(err.response.data)

//Axios POST call to create recipe
const createRecipe = (body,id) => axios.post(`${postrecipeURL}/${id}`, body).then(recipesCallback).catch(errCallback)

//Axios DELETE call to delete recipe
const deleteRecipe = id => axios.delete(`${deleterecipeURL}/${id}`).then(recipesCallback).catch(errCallback)

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
                                   <br/><p class="card-text">Recipe Name: ${recipe.recipeName}</p><br/>
                                   <p class="card-text">Ingredients: <br/> ${recipe.recipeIngredients}</p><br/>
                                   <p class="card-text">Instructions: <br/>
                                     ${recipe.recipeDescription}</p>`

   let cardDelete = "";
   if(userId === `${recipe.userId}`)
   {
     cardDelete=`<button type="button"  class="btn" id="deleteRecipeBtn" onclick=deleteRecipe(${recipe.id})>Delete Recipe</button>`
   }
    let cardEnd=`</div></div></div>`

    recipeCard.innerHTML =  cardStart+cardDelete+cardEnd;

    recipesContainer.appendChild(recipeCard)
}

//This is callback function that is called after receiving data from server
function displayRecipes(arr) {
    recipesContainer.innerHTML = ''
    for (let i = 0; i < arr.length; i++) {
        createRecipeCard(arr[i])
    }
}

//This method is to show Create Recipe form
function showRecipeForm()
{
    document.getElementById("recipe-form").style.display="block"
    document.getElementById("createRecipe_createdBy").value=userId;
}
document.getElementById("recipe-form").style.display="none"

form.addEventListener('submit', submitHandler)

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