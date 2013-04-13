\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                                                      BroomSticks
                                                      
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Have you ever wanted to fly like Harry Potter?
Have you ever want to soar at incredible speeds with on the Firebolt, the fastest broomstick in production?
Or, have you ever just wanted a convenient mode of transportation that does require rails or water?
Well this plugin adds Broomsticks to the game. Have as many different broomstick models as you want.
Give them any name you want. Customize their speeds and even pick what items can be broomsticks.

Riding and Making Brooms:

1. First you have to craft an acceptable broom item as specified in config.yml (default is a shovel)
2. Enchant the item with Infinity 1 using an anvil.
3. Equip the broom in your hot bar.
4. Right click and sneak to go in the direction you are looking.
5. Just right click to glide forward slower.

Config:

Default Config:

    Brooms:
      number-of-brooms: 5
      broom1:
         name: Cleansweep One
         speed: 0.7
        # wood shovel
         item: 269
      
      broom2:
        name: Comet 140
        speed: 0.9
        # stone shovel
        item: 273
      
      broom3:
        name: Nimbus 2000
        speed: 1.3
        # iron shovel
        item: 256
      
      broom4:
        name: Nimbus 2001
        speed: 1.4
        # gold shovel
        item: 284
      
      broom5:
        name: Firebolt
        speed: 2.0
        # diamond shovel
        item: 277

* feel free to edit any brooms or add your own.
* Don't put speeds that are to high or low. (the fastest default broom as is goes pretty fast)
* make sure to use the correct item id value for the items you want to be brooms.
* don't use a ton of special characters for the broom model name because not all of them might work.
* Important Note: make sure that number-of-brooms: is equal to the amount of brooms on your list.

Known Issues:

* when enchanting the broom item it appears to be duplicated but the second item will disappear when you click on it.
