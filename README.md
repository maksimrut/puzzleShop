#PUZZLESHOP
##Mechanical puzzles online store

The application is a store for selling mechanical puzzles. Application allows to choose and order puzzles with various difficulty level. 
Data is stored in MySQL 8.0 database.  

##Roles
There are three role in the application: guest, customer and administrator.

GUEST may:
- Sign in
- Sign up
- Change the language
- Look through puzzles
- Visit pages "About us" and "Main"

CUSTOMER may:
- Log out
- Change the language
- Look through puzzles
- Visit pages "About us" and "Main"
- Add chosen goods to basket
- Look through basket content
- Add or remove items in the basket
- Make an order
- Look through his orders
- Look each order info
- Cancel order (if it not completed)
- Look through his profile data
- Update password
- Update profile data

ADMIN may:
- Log out
- Change the language
- Look through puzzles
- Visit pages "About us" and "Main"
- - Look through his profile data
- Update password
- Update profile data
- Edit each puzzle data
- Delete puzzle
- Add new puzzle
- Look through all orders
- Look each order info
- Cancel order
- Change order status to COMPLETED
- Reactivate order
- Delete order
- Look through all users
- Delete user (except himself)
- Block customer
- Change from CUSTOMER to ADMIN and vice versa
