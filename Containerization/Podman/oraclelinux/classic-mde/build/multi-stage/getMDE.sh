# LICENSE UPL 1.0
#
# Copyright (c) 2019 Oracle and/or its affiliates. All rights reserved.
#
# Since: March, 2020
# Author: duncan.ford@oracle.com
# Description: Retrieve Siebel MDE installation media
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

set -x
wget -r -q -np -nH --cut-dirs=2 -l 0 -R index.html* http://$1/siebel/$2/Siebel_Enterprise_Server/