\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                                                    BroomSticks

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Have you ever wanted to fly like Harry Potter?
Have you ever want to soar at incredible speeds with on the Firebolt, the fastest broomstick in production?
Or, have you ever just wanted a convenient mode of transportation that does require rails or water?
Well this plugin adds Broomsticks to the game. Have as many different broomstick models as you want.
Give them any name you want. Customize their speeds and even pick what items can be broomsticks.

Riding and Making Brooms:

1. First you have to craft an acceptable broom item as specified in config.yml (default is a shovel)
2. Enchant the item with Infinity 1 using an anvil.
3. Equip the broom in your hot bar.
4. Right click to mount the broom.
5. <s>While mounted you will glide at minimum speed.</s>
6. <s>Hold space bar to increase the blue bar on the screen and then let go. The more you fill the blue bar the faster you will go.</s>
7. <s>Stay out of trouble, brooms will now burn up after touching lava or being lit on fire.</s>
8. Sneak to dismount the broom. Make sure you are on the ground first or you will fall. Dismounting a broom also conserves momentum so flying fast means you'll land a little further than you might expect. 

Config:

Default Config:

    #quidditch-pitch-radius: 75 this is not a feature yet
    
    Brooms:
      #dont use spaces when naming the brooms. Use underscore instead.
      #Ex. name: Name_with_spaces
      #use a $ to declare a color or formatting code
      #Ex. $9 = blue text
      #for more color and formatting codes go to http://minecraft.gamepedia.com/Color_codes
      #don't touch the durability it is not a feature yet
      number-of-brooms: 5
  
      broom1:
        name: $9Cleansweep_One
        speed: 0.5
        durability: 100
        # wood shovel
        item: 269
      
      broom2:
        name: $9Comet_140
        speed: 0.8
        durability: 100
        # stone shovel
        item: 273
      
      broom3:
        name: $9Nimbus_2000
        speed: 1.0
        durability: 100
        # iron shovel
        item: 256
      
      broom4:
        name: $9Nimbus_2001
        speed: 1.1
        durability: 100
        # gold shovel
        item: 284
      
      broom5:
        name: $9Firebolt
        speed: 1.5
        durability: 100
        # diamond shovel
        item: 277

* durability isn't a functioning feature yet. It sets the damage needed to break a broom.
* feel free to edit any brooms or add your own.
* Don't put speeds that are too high or low. (the fastest default broom as is goes pretty fast)
* make sure to use the correct item id value for the items you want to be brooms.
* don't use a ton of special characters for the broom model name because not all of them might work.
* avoid using strange items such as bows, potions, or food as brooms because they have separate right click functions. 

Known Issues:

* when enchanting the broom item it appears to be duplicated but the second item will disappear when you click on it.
