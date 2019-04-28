const uuid = require('uuid/v4')
const json = require('./matches.json');

const createTable = 'create table profile (id uuid not null, age int4, lat numeric(19, 2), lon numeric(19, 2), name varchar(255), compatibility_score int8, contacts_exchanged int4, display_name varchar(255), favourite boolean not null, height_in_cm int8, job_title varchar(255), main_photo varchar(255), religion varchar(255), primary key (id));';
const insertInstruction = 'insert into profile('
  .concat('id,display_name,age,job_title,height_in_cm,name,lat,lon,main_photo,contacts_exchanged,favourite,religion)')
  .concat(' values');

const values = json.matches.map(m => {
  const out = `('${uuid()}'`
    .concat(`,'${m.display_name}'`)
    .concat(`,${m.age}`)
    .concat(`,'${m.job_title}'`)
    .concat(`,${m.height_in_cm}`)
    .concat(`,'${m.city.name}'`)
    .concat(`,${m.city.lat}`)
    .concat(`,${m.city.lon}`)
    .concat(`,'${m.main_photo}'`)
    .concat(`,${m.contacts_exchanged}`)
    .concat(`,${m.favourite}`)
    .concat(`,'${m.religion}')`);

  return out;
});

process.stdout.write(insertInstruction+'\n'+values.join(',\n'));