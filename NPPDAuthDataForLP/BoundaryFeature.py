# -*- coding: utf-8 -*-
"""
Created on Tue May 08 10:39:11 2018

@author: WILCBM
"""

import urllib

class BoundaryFeature:
    features = []
    
    def consume(self, eventJson, geometry, appid):
        if geometry['rings'] is not None:
            featureData = { 'attributes' : {
                    'eventid' : eventJson['data']['id'].encode('utf-8'),
                    'appid' : appid.encode('utf-8')
                    }, 'geometry' : {
                            'rings' : geometry['rings']
                            } }
        else:
            featureData = { 'attributes' : {
                    'eventid' : eventJson['data']['id'].encode('utf-8'),
                    'appid' : appid.encode('utf-8')
                    }, 'geometry' : {
                            'curveRings' : geometry['curveRings']
                            } }
        
        self.features = [featureData]
        
    def urlEncode(self):
        urlTuple = {
                'features' : self.features,
                'f' : 'json',
                'rollbackOnFailure' : True,
                'gdbVersion' : None
                }
        
        return urllib.urlencode(urlTuple)

