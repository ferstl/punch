package com.github.marschall.punch;

/**
 * Concepts:
 *  - a VM crash is treated like a termination due to an exception
 *  - the state of a task is either
 *   - not yet run (implicit)
 *   - running
 *   - finished
 *  - in a case of a re-run all not finished tasks are run again
 *  - a task is identified by a path
 *  - in a recovery case the job tree looks exactly the same
 */