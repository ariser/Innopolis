int sheep = 0,
    wolf = 1,
    cabbage = 2,
    farmer = 3;

// false is the initial river bank,
// true is the other river bank
bool bank[4] = false;

proctype solve() {
    byte i = 0;

    // precondition: all targets are on the same bank, and the bank is 'false'
    assert((bank[0] || bank[1] || bank[2] || bank[3]) == false);

    do
        :: ((bank[0] && bank[1] && bank[2] && bank[3]) == false) ->
            int target = -1;
            do
            :: (bank[farmer] == bank[sheep]) -> target = sheep;
            :: (bank[farmer] == bank[wolf]) -> target = wolf;
            :: (bank[farmer] == bank[cabbage]) -> target = cabbage;
            :: target = farmer;
            :: (target != -1) -> break;
            od;

            // loop invariant: target is either sheep, wolf, cabbage of farmer
            assert(target >= 0 && target <= 3);

            // loop invariant: only farmer can drive the boat
            assert (bank[target] == bank[farmer]);

            if
                :: (target != farmer) -> bank[farmer] = !bank[farmer];
                :: else
            fi;
            bank[target] = !bank[target];

            if
            :: (target == sheep) -> printf("\n===============================\nMOVING SHEEP\n===============================\n");
            :: (target == wolf) -> printf("\n===============================\nMOVING WOLF\n===============================\n");
            :: (target == cabbage) -> printf("\n===============================\nMOVING CABBAGE\n===============================\n");
            :: (target == farmer) -> printf("\n===============================\nMOVING FARMER\n===============================\n");
            fi;

            // loop invariant: neither sheep is alone with cabbage,
            assert (!(bank[sheep] == bank[cabbage] && bank[sheep] != bank[farmer]));

            // loop invariant: nor wolf is alone with sheep
            assert (!(bank[sheep] == bank[wolf] && bank[sheep] != bank[farmer]));

            i++;
        :: else -> break
    od;

    // postcondition: all targets are on the same bank, and the bank is 'true'
    assert(bank[0] && bank[1] && bank[2] && bank[3]);
}

init {
    run solve()
}
