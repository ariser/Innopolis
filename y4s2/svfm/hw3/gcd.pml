proctype GCD(int a, b) {
    int old_a = a, old_b = b;

    //precondition
    assert (a > 0 && b > 0);

    do
        //loop invariant
        :: assert(a > 0 && b > 0);

        :: (a > b) -> a = a - b
        :: (a < b) -> b = b - a
        :: (a == b) -> break

    od;

    printf("\n\n==========================\n\n== GCD of %d and %d: %d ==\n\n==========================\n\n", old_a, old_b, a);

    //postconditions
    assert(a > 0);
    assert(a <= old_a && a <= old_b);
    assert(old_a % a == 0 && old_b % a == 0);
}

init {
    run GCD(78, 585);
}
